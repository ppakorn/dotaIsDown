package advent2020

import java.io.File

fun main() {
    val map = mutableMapOf<String, MutableSet<Pair<Int, String>>>()
    File("/Users/ampos/Documents/dota_is_down/LeetCode/leetcode-kotlin/src/advent2020/Day7.input").forEachLine { line ->
        val a = line.split(';')
        val outer = a[0]
        if (a[1] == "noother") return@forEachLine

        val inners = a[1].split(',')
        inners.forEach { inner ->
            val b = inner.split(':')
            val pair = Pair(b[0].toInt(), b[1])
            if (map[outer] == null) {
                map[outer] = mutableSetOf(pair)
            } else {
                map[outer]!!.add(pair)
            }
        }
    }

    val d = Day7(map)
    d.execute(setOf(Triple(1, 1, "shinygold")))
    println(d.count - 1)
}

class Day7(private val map: Map<String, MutableSet<Pair<Int, String>>>) {
    var count = 0
    fun execute(bags: Set<Triple<Int, Int, String>>) {
        if (bags.isEmpty())
            return

        val newBags = mutableSetOf<Triple<Int, Int, String>>()
        bags.forEach { (multiplier, amount, inner) ->
            count += multiplier * amount
            map[inner]?.forEach { pair ->
                newBags.add(Triple(multiplier * amount, pair.first, pair.second))
                Unit
            }
        }

        execute(newBags)
    }
}