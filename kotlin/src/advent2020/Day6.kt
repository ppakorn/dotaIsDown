package advent2020

import java.io.File

fun main() {
    var set = setOf<Char>()
    var count = 0
    var new = true
    File("/Users/ampos/Documents/dota_is_down/LeetCode/leetcode-kotlin/src/advent2020/Day6.input").forEachLine { line ->
        if (line.isEmpty()) {
            count += set.count()
            new = true
            return@forEachLine
        }

        set = if (new) {
            new = false
            line.toCharArray().toSet()
        } else {
            val newSet = line.toCharArray().toSet()
            set intersect newSet
        }
    }
    println(count)
}