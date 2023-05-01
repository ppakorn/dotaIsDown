package advent2021

import java.io.File

class Day8 {
    private val segments = mapOf(
        2 to listOf(setOf('2', '5')),
        3 to listOf(setOf('0', '2', '5')),
        4 to listOf(setOf('1', '2', '3', '5')),
        5 to listOf(
            setOf('0', '2', '3', '4', '6'),
            setOf('0', '2', '3', '5', '6'),
            setOf('0', '1', '3', '5', '6')
        ),
        6 to listOf(
            setOf('0', '1', '2', '4', '5', '6'),
            setOf('0', '1', '3', '4', '5', '6'),
            setOf('0', '1', '2', '3', '5', '6')
        ),
        7 to listOf(
            setOf('0', '1', '2', '3', '4', '5', '6')
        )
    )

    private val segmentToNum = mapOf(
        setOf('2', '5') to "1",
        setOf('0', '2', '5') to "7",
        setOf('1', '2', '3', '5') to "4",
        setOf('0', '2', '3', '4', '6') to "2",
        setOf('0', '2', '3', '5', '6') to "3",
        setOf('0', '1', '3', '5', '6') to "5",
        setOf('0', '1', '2', '4', '5', '6') to "0",
        setOf('0', '1', '3', '4', '5', '6') to "6",
        setOf('0', '1', '2', '3', '5', '6') to "9",
        setOf('0', '1', '2', '3', '4', '5', '6') to "8"
    )

    fun execute() {
        val inputs = mutableListOf<Pair<List<String>, List<String>>>()
        File("/Users/ampos/Documents/dota_is_down/kotlin/src/advent2021/Day8.input").forEachLine { line ->
            val a = line.split(" | ")
            val signals = a[0].split(' ').sortedBy { it.length }
            val outputs = a[1].split(' ')
            inputs.add(Pair(signals, outputs))
        }

        var count = 0
        inputs.forEach {
            val order = findOrder(it.first)
            val map = recur(order, 0, it.first, emptyMap())
            val output = it.second.map { digit ->
                digit.map { char ->
                    map[char]!!
                }.toCharArray().toSet()
            }.map { set ->
                segmentToNum[set]!!
            }.joinToString("").toInt()
            count += output
        }
        println(count)
    }

    private fun recur(order: List<Char>, current: Int, signals: List<String>, map: Map<Char, Char>): Map<Char, Char> {
        if (map.size == 7) {
            return map
        }

        val c = order[current]
        ('0'..'6').forEach { i ->
            if (i in map.values) {
                return@forEach
            }

            val newMap = map.toMutableMap()
            newMap[c] = i
            val mapSignals = replaceSignal(signals, Pair(c, i))
            if (check(mapSignals)) {
                val result = recur(order, current + 1, mapSignals, newMap)
                if (result.size == 7) {
                    return result
                }
            }
        }

        return emptyMap()
    }

    private fun findOrder(signals: List<String>): List<Char> {
        val all = signals.joinToString("")
        return all.toCharArray().distinct()
    }

    private fun replaceSignal(signals: List<String>, newMap: Pair<Char, Char>): List<String> {
        return signals.map { it.replace(oldChar = newMap.first, newChar = newMap.second) }
    }

    private fun check(mapSignals: List<String>): Boolean {
        mapSignals.forEach outer@{ signal ->
            val num = signal.filter { it <= '6' }.toCharArray().toSet()
            val compare = segments[signal.length]
            compare?.forEach { seg ->
                if (seg.containsAll(num)) {
                    return@outer
                }
            }
            return false
        }

        return true
    }
}

fun main() {
    val d = Day8()
    d.execute()
}