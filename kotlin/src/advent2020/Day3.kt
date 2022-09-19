package advent2020

import java.io.File

fun main() {
    var count = 0
    var cursor = 0
    val right = 1
    var countThisLine = true
    File("/Users/ampos/Documents/dota_is_down/LeetCode/leetcode-kotlin/src/advent2020/Day3.input").forEachLine { line ->
        countThisLine = !countThisLine
        if (!countThisLine) return@forEachLine

        val lineSize = line.count()
        cursor = (cursor + right) % lineSize
        if (line[cursor] == '#') {
            count += 1
        }
    }
    println(count)
}