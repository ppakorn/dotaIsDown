package advent2021

import java.io.File
import kotlin.math.abs
import kotlin.math.round

class Day7 {
    fun execute() {
        File("/Users/ampos/Documents/dota_is_down/kotlin/src/advent2021/Day7.input").forEachLine { line ->
            val crabs = line.split(",").map { it.toInt() }.sorted()
//            val mod = findMod(crabs)
//            println(findCost(crabs, 374175.0))
            val mean = findMean(crabs)
            println(mean)
            println(findCost2(crabs, 493))
        }
    }

    private fun findCost(crabs: List<Int>, point: Int): Int {
        var cost = 0
        crabs.forEach {
            cost += abs(it - point)
        }
        return cost
    }

    private fun findCost2(crabs: List<Int>, point: Int): Int {
        var cost = 0
        crabs.forEach {
            val a = abs(it - point)
            cost += a * (a + 1) / 2
        }
        return cost
    }

    private fun findMedian(crabs: List<Int>): Int {
        return if (crabs.size % 2 == 0) {
            (crabs[crabs.size / 2 - 1] + crabs[crabs.size / 2]) / 2
        } else {
            crabs[crabs.size / 2]
        }
    }

    private fun findMean(crabs: List<Int>): Double {
        return crabs.average()
    }
}

fun main() {
    val d = Day7()
    d.execute()
}