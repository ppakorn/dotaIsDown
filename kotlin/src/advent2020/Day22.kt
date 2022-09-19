package advent2020

import java.io.File

class Day22 {

    private val p1 = mutableListOf<Int>()
    private val p2 = mutableListOf<Int>()
    private var finalList = listOf<Int>()

    fun execute() {
        readInput()
//        play(p1, p2, mutableSetOf(), 0)
        // First version stackoverflow error

        play2(p1, p2, mutableSetOf())
        println(score(finalList))
    }

    private fun score(l1: List<Int>): Int {
        var sum = 0
        val len = l1.count()
        l1.forEachIndexed { i, x ->
            sum += (len - i) * x
        }
        return sum
    }

    // return true when p1 win
    private fun play(l1: MutableList<Int>, l2: MutableList<Int>, mem: MutableSet<String>, level: Int): Boolean {
        println(level)
        if (l1.isEmpty()) {
            finalList = l2
            return false
        }

        if (l2.isEmpty()) {
            finalList = l1
            return true
        }

        val key = key(l1, l2)
        if (key in mem) {
            finalList = l1
            return true
        }
        mem.add(key)

        val a1 = l1.removeFirst()
        val a2 = l2.removeFirst()

        if (l1.count() >= a1 && l2.count() >= a2) {
            val subL1 = l1.slice(0 until a1).toMutableList()
            val subL2 = l2.slice(0 until a2).toMutableList()
            val r = play(subL1, subL2, mutableSetOf(), level + 1)
            if (r) {
                l1.add(a1)
                l1.add(a2)
            } else {
                l2.add(a2)
                l2.add(a1)
            }
        } else if (a1 > a2) {
            l1.add(a1)
            l1.add(a2)
        } else {
            l2.add(a2)
            l2.add(a1)
        }

        return play(l1, l2, mem, level)
    }

    private fun play2(l1: MutableList<Int>, l2: MutableList<Int>, mem: MutableSet<String>): Boolean {
        while (l1.isNotEmpty() && l2.isNotEmpty()) {
            val key = key(l1, l2)
            if (key in mem) {
                return true
            }
            mem.add(key)

            val a1 = l1.removeFirst()
            val a2 = l2.removeFirst()

            if (l1.count() >= a1 && l2.count() >= a2) {
                val subL1 = l1.slice(0 until a1).toMutableList()
                val subL2 = l2.slice(0 until a2).toMutableList()
                val r = play2(subL1, subL2, mutableSetOf())
                if (r) {
                    l1.add(a1)
                    l1.add(a2)
                } else {
                    l2.add(a2)
                    l2.add(a1)
                }
            } else if (a1 > a2) {
                l1.add(a1)
                l1.add(a2)
            } else {
                l2.add(a2)
                l2.add(a1)
            }
        }

        finalList = l1 + l2
        return l2.isEmpty()
    }

    private fun key(l1: List<Int>, l2: List<Int>): String {
        val s1 = l1.map { 'A' + it }.joinToString("")
        val s2 = l2.map { 'A' + it }.joinToString("")
        return "$s1|$s2"
    }

    private fun readInput() {
        var p1Card = true
        File("/Users/ampos/Documents/dota_is_down/kotlin/src/advent2020/Day22.input").forEachLine { line ->
            if (line.isBlank()) {
                return@forEachLine
            }

            if (line == "Player 2:") {
                p1Card = false
                return@forEachLine
            }

            val a = line.toIntOrNull() ?: return@forEachLine

            if (p1Card) {
                p1.add(a)
            } else {
                p2.add(a)
            }
        }
    }
}

fun main() {
    val d = Day22()
    d.execute()
}