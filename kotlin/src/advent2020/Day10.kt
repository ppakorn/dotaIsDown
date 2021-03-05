package advent2020

import java.io.File

fun main() {
    var currentJolt = 0
    var max = -1
    var count1 = 0
    var count3 = 0
    val jolts = mutableListOf(0)
    File("/Users/ampos/Documents/dota_is_down/LeetCode/leetcode-kotlin/src/advent2020/Day10.input").forEachLine { line ->
        jolts.add(line.toInt())
    }

    jolts.sort()
//    var previous = 0
//    jolts.forEach { jolt ->
//        when (jolt - previous) {
//            1 -> count1 += 1
//            3 -> count3 += 1
//        }
//        previous = jolt
//    }
//
//    count3 += 1
//    println(count1 * count3)

    val a = Array(jolts.count()) { 1L }
    (1 until jolts.count()).forEach { i ->
        var j = 1
        var count = 0L
        while (i - j >= 0 && jolts[i] - jolts[i - j] <= 3) {
            count += a[i - j]
            j += 1
        }
        a[i] = count
    }
    println(a.last())
}
