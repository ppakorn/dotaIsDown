package advent2021

import java.io.File
import java.util.ArrayDeque

class Day10 {
    private val opens = setOf('(', '<', '{', '[')
    fun execute() {
        var corrupt = 0
        val incomplete = mutableListOf<Long>()
        File("/Users/ampos/Documents/dota_is_down/kotlin/src/advent2021/Day10.input").forEachLine line@{ line ->
            val stack = ArrayDeque<Char>()
            line.forEach { c ->
                if (c in opens) {
                    stack.push(c)
                } else {
                    val match = stack.pop()
                    if (c == ')' && match != '(') {
                        corrupt += 3
                        stack.clear()
                        return@line
                    } else if (c == ']' && match != '[') {
                        corrupt += 57
                        stack.clear()
                        return@line
                    } else if (c == '}' && match != '{') {
                        corrupt += 1197
                        stack.clear()
                        return@line
                    } else if (c == '>' && match != '<') {
                        corrupt += 25137
                        stack.clear()
                        return@line
                    }
                }
            }
            incomplete.add(calculateCompletion(stack))
        }

        val sorted = incomplete.sorted()
        println(sorted[sorted.size / 2])
    }

    private fun calculateCompletion(stack: ArrayDeque<Char>): Long {
        var score = 0L
        while (stack.isNotEmpty()) {
            val a = stack.pop()
            when (a) {
                '(' -> {
                    score = score * 5 + 1
                }
                '[' -> {
                    score = score * 5 + 2
                }
                '{' -> {
                    score = score * 5 + 3
                }
                '<' -> {
                    score = score * 5 + 4
                }
            }
        }
        return score
    }
}

fun main() {
    val d = Day10()
    d.execute()
}