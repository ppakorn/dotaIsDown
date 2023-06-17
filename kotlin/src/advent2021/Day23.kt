package advent2021

import java.lang.Exception
import kotlin.math.abs

class Day23 {
    private val charEntrances = mapOf(
        'A' to 2,
        'B' to 4,
        'C' to 6,
        'D' to 8
    )
    private val multipliers = mapOf(
        'A' to 1,
        'B' to 10,
        'C' to 100,
        'D' to 1000
    )
    private val roomEntrances = mapOf(
        11 to 2,
        12 to 2,
        13 to 2,
        14 to 2,
        15 to 4,
        16 to 4,
        17 to 4,
        18 to 4,
        19 to 6,
        20 to 6,
        21 to 6,
        22 to 6,
        23 to 8,
        24 to 8,
        25 to 8,
        26 to 8,
    )
    private val rooms = mapOf(
        'A' to listOf(11, 12, 13, 14),
        'B' to listOf(15, 16, 17, 18),
        'C' to listOf(19, 20, 21, 22),
        'D' to listOf(23, 24, 25, 26),
    )
    private val visited = mutableMapOf<String, Int>()

    fun execute(input: String) {
        executeRecursive(input, 0)
        println("Done")
        println(visited["...........AAAABBBBCCCCDDDD"])
    }

    private fun executeRecursive(state: String, currentCost: Int): Boolean {
        if (currentCost > 60000) return false
        if (visited[state] != null && visited[state]!! <= currentCost) return false
        visited[state] = currentCost

        if (state == "...........AAAABBBBCCCCDDDD") return true

        val moves = findMovableIndexes(state)
        moves.forEach { (source, destination) ->
            val newCost = calculateCost(state, source, destination)
            val newState = move(state, source, destination)
            val correct = executeRecursive(newState, currentCost + newCost)
            if (correct) {
                printState(state)
                return true
            }
        }
        return false
    }

    // Return Pair of source, destination indexes
    fun findMovableIndexes(state: String): List<Pair<Int, Int>> {
        val possibleMoves = state.mapIndexedNotNull { index, _ ->
            if (isInGoodPosition(state, index)) return@mapIndexedNotNull null
            val destinations = findDestinations(state, index)
            if (destinations.isEmpty()) {
                null
            } else {
                destinations.map { Pair(index, it) }
            }
        }.flatten()
        return possibleMoves
    }

    fun isInGoodPosition(state: String, i: Int): Boolean {
        return when (val o = state[i]) {
            '.' -> false
            else -> {
                val r = rooms[o]!!
                if (i !in r) return false
                val indexRoom = r.indexOf(i)
                if (indexRoom == r.lastIndex) return true
                (indexRoom+1..r.lastIndex).forEach {
                    if (state[r[it]] != o) return false
                }
                return true
            }
        }
    }

    fun findDestinations(state: String, i: Int): List<Int> {
        val char = state[i]
        if (char == '.') return emptyList()
        if (!canMoveToEntrance(state, i)) return emptyList()

        when (i) {
            in 0..10 -> {
                val deepestPositionInRoom = findDeepestPositionInRoom(state, char) ?: return emptyList()
                return listOf(deepestPositionInRoom)
            }
            else -> {
                return findHallwayPositions(state, roomEntrances[i]!!)
            }
        }
    }

    private fun canMoveToEntrance(state: String, i: Int): Boolean {
        val char = state[i]
        if (char == '.') return false

        when (i) {
            in 0..10 -> {
                val entrance = charEntrances[char]!!
                if (i < entrance) {
                    for (j in i+1 until entrance) {
                        if (state[j] != '.') return false
                    }
                } else {
                    for (j in entrance+1 until i) {
                        if (state[j] != '.') return false
                    }
                }
                return true
            }
            11, 15, 19, 23 -> return true
            12, 16, 20, 24 -> {
                return state[i-1] == '.'
            }
            13, 17, 21, 25 -> {
                return state[i-1] == '.' && state[i-2] == '.'
            }
            14, 18, 22, 26 -> {
                return state[i-1] == '.' && state[i-2] == '.' && state[i-3] == '.'
            }
        }
        printState(state)
        throw Exception("canMoveToEntrance doesn't handle all cases")
    }

    // Return new position (deepest in room), null if cannot go in
    fun findDeepestPositionInRoom(state: String, char: Char): Int? {
        val r = rooms[char]!!
        var newPosition: Int? = null
        var phaseEmpty = true
        r.forEach {
            if (phaseEmpty && state[it] == '.') {
                newPosition = it
                return@forEach
            }
            phaseEmpty = false
            if (state[it] != char) return null
        }

        return newPosition
    }

    // Return list of available slot in hallway
    private fun findHallwayPositions(state: String, entrance: Int): List<Int> {
        val totalSlot = listOf(0, 1, 3, 5, 7, 9, 10)
        val available = totalSlot.filter { slot ->
            if (state[slot] != '.') return@filter false
            if (slot < entrance) {
                for (j in slot+1 until entrance) {
                    if (state[j] != '.') return@filter false
                }
            } else {
                for (j in entrance+1 until slot) {
                    if (state[j] != '.') return@filter false
                }
            }
            return@filter true
        }
        return available
    }

    private fun move(state: String, source: Int, destination: Int): String {
        val newState = state.toCharArray()
        newState[source] = state[destination]
        newState[destination] = state[source]
        return newState.joinToString("")
    }

    private fun calculateCost(state: String, source: Int, destination: Int): Int {
        val multiplier = multipliers[state[source]]!!
        // calculate going into room or out of room is the same
        // swap to make it is always into room (0-10 to 11-26)
        var s = source
        var d = destination
        if (source > 10) {
            s = destination
            d = source
        }

        val entrance = roomEntrances[d]!!
        val distanceToEntrance = abs(s - entrance)
        val distanceToPositionInRoom = when (entrance) {
            2 -> { d - 10 }
            4 -> { d - 14 }
            6 -> { d - 18 }
            8 -> { d - 22 }
            else -> {
                println(state)
                throw Exception("Wrong source and destination")
            }
        }

        return (distanceToEntrance + distanceToPositionInRoom) * multiplier
    }

    private fun printState(s: String) {
        println(s.slice(0..10))
        println("  ${s[11]} ${s[15]} ${s[19]} ${s[23]}")
        println("  ${s[12]} ${s[16]} ${s[20]} ${s[24]}")
        println("  ${s[13]} ${s[17]} ${s[21]} ${s[25]}")
        println("  ${s[14]} ${s[18]} ${s[22]} ${s[26]}")
        println()
    }
}

fun main() {
    val d = Day23()
    val input = "...........ADDCDCBCABADBACB"
    d.execute(input)
}