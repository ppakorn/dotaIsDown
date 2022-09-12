package advent2021

import java.io.File

class Day14 {
    fun execute() {
        val initial = "NCOPHKVONVPNSKSHBNPF"
        val formula = mutableMapOf<String, String>()
        File("/Users/ampos/Documents/dota_is_down/kotlin/src/advent2021/Day14.input").forEachLine { line ->
            val a = line.split(" -> ")
            formula[a[0]] = a[1]
        }

        var polymer = initial
        repeat(40) {
            var newPolymer = ""
            (0 until polymer.length - 1).forEach { i ->
                val elements = polymer.substring(i, i + 2)
                val newElement = formula[elements] ?: ""
                newPolymer += polymer[i] + newElement
            }
            newPolymer += polymer.last()
            polymer = newPolymer
        }

        val count = count(polymer)
        println(count.values.maxOrNull()!! - count.values.minOrNull()!!)
    }

    private fun count(polymer: String): Map<Char, Int> {
        val count = mutableMapOf<Char, Int>()
        polymer.forEach { c ->
            if (count[c] == null) {
                count[c] = 0
            }
            count[c] = count[c]!! + 1
        }
        return count
    }

    fun execute2() {
        val initial = "NCOPHKVONVPNSKSHBNPF"
        var currentPolymer = mutableMapOf<String, Long>()
        initial.forEachIndexed { i, c ->
            if (i == initial.lastIndex) return@forEachIndexed
            val elements = "$c${initial[i + 1]}"
            currentPolymer[elements] = (currentPolymer[elements] ?: 0) + 1
        }

        val formula = mutableMapOf<String, String>()
        File("/Users/ampos/Documents/dota_is_down/kotlin/src/advent2021/Day14.input").forEachLine { line ->
            val a = line.split(" -> ")
            formula[a[0]] = a[1]
        }

        repeat(40) {
            val newPolymer = currentPolymer.toMutableMap()
            currentPolymer.forEach { (elements, oldCount) ->
                val newC = formula[elements] ?: return@forEach
                val new1 = elements[0] + newC
                val new2 = newC + elements[1]

                newPolymer[elements] = newPolymer[elements]!! - oldCount
                if (newPolymer[elements]!! < 0) {
                    throw Exception("round $it: Why < 0")
                }

                newPolymer[new1] = (newPolymer[new1] ?: 0) + oldCount
                newPolymer[new2] = (newPolymer[new2] ?: 0) + oldCount
            }
            currentPolymer = newPolymer
        }

        val count = count2(currentPolymer, initial.first(), initial.last())
        println(count.maxOrNull()!! - count.minOrNull()!!)
    }

    private fun count2(polymer: Map<String, Long>, firstChar: Char, lastChar: Char): List<Long> {
        val count = mutableMapOf<Char, Long>()
        polymer.forEach { (elements, occur) ->
            count[elements[0]] = (count[elements[0]] ?: 0) + occur
            count[elements[1]] = (count[elements[1]] ?: 0) + occur
        }
        count[firstChar] = (count[firstChar] ?: 0) + 1
        count[lastChar] = (count[lastChar] ?: 0) + 1

        return count.map { (c, n) ->
            if (n % 2L == 1L) {
                throw Exception("Why $c odd")
            }
            n / 2
        }
    }
}

fun main() {
    val d = Day14()
    d.execute2()
}