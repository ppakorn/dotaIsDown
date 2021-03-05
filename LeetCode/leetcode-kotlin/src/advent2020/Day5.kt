package advent2020

import java.io.File

fun main() {
    val set = mutableSetOf<Int>()
    File("/Users/ampos/Documents/dota_is_down/LeetCode/leetcode-kotlin/src/advent2020/Day5.input").forEachLine { line ->
        val i = Integer.parseInt(line, 2)
        set.add(i)
    }
    val full = (0..1023).toSet()
    println(full - set)
}