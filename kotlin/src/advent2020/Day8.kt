package advent2020

import java.io.File
import java.lang.Exception

fun main() {
    val instructions = mutableListOf<Pair<String, Int>>()
    File("/Users/ampos/Documents/dota_is_down/LeetCode/leetcode-kotlin/src/advent2020/Day8.input").forEachLine { line ->
        val a = line.split(' ')
        val instruction = a[0]
        val offset = a[1].toInt()
        instructions.add(Pair(instruction, offset))
    }

    val d8 = Day8()
    val loopSet = d8.execute(instructions)
    var lastEditLine: Pair<Int, Pair<String, Int>>? = null
    loopSet.forEach { lineNumber ->
        lastEditLine?.let {
            instructions[it.first] = it.second
        }

        val instruction = instructions[lineNumber]
        when (instruction.first) {
            "acc" -> return@forEach
            "nop" -> {
                lastEditLine = Pair(lineNumber, instruction)
                instructions[lineNumber] = Pair("jmp", instruction.second)
            }
            "jmp" -> {
                lastEditLine = Pair(lineNumber, instruction)
                instructions[lineNumber] = Pair("nop", instruction.second)
            }
        }

        d8.execute(instructions)
    }
}

class Day8 {
    fun execute(instructions: List<Pair<String, Int>>): Set<Int> {
        var currentLine = 0
        val executedLine = mutableSetOf<Int>()
        var acc = 0
        while (true) {
            val instruction = instructions[currentLine]
            val nextLine = when (instruction.first) {
                "nop" -> {
                    currentLine + 1
                }
                "acc" -> {
                    acc += instruction.second
                    currentLine + 1
                }
                "jmp" -> {
                    currentLine + instruction.second
                }
                else -> throw Exception("type error")
            }
            executedLine.add(currentLine)
            if (executedLine.contains(nextLine)) {
                return executedLine
            } else if (currentLine == instructions.count() - 1) {
                println(acc)
                throw Exception("finish")
            }
            currentLine = nextLine
        }
    }
}
