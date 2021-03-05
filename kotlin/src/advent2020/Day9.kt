package advent2020

import Solution1
import java.io.File
import kotlin.system.exitProcess

fun main() {
    val n = Array(1000) { 0 }
    var index = -1
    val s = Solution1()
    val pre = 25
    val wrongOne = 466456641
    var sum = 0
    var first = 0
    File("/Users/ampos/Documents/dota_is_down/LeetCode/leetcode-kotlin/src/advent2020/Day9.input").forEachLine { line ->
        index += 1

        val value = line.toInt()
        n[index] = value

//        if (index < pre) return@forEachLine
//
//        val slice = n.slice(IntRange(index - pre, index - 1)).toIntArray()
//        val result = s.twoSum(slice, value)
//
//        if (result.isEmpty()) {
//            println(value)
//            exitProcess(0)
//        }

        sum += value

        while (sum > wrongOne) {
            sum -= n[first]
            first += 1
        }

        if (sum == wrongOne) {
            val result = n.sliceArray(first..index)
            val min = result.min()!!
            val max = result.max()!!
            println(min + max)
            exitProcess(0)
        }
    }
}