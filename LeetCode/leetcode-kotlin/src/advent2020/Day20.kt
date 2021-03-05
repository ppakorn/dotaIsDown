package advent2020

import java.io.File


class Day20 {
    private val part1Tiles = mutableListOf<List<Pair<Int, Int>>>()
    private val tileNumbers = mutableMapOf<Int, Int>()

    private val count = mutableMapOf<Int, Int>()

    fun execute() {
        readInput()
        countDuplicateBorders()

        println("[1]")
        println(count.filter { it.value == 1 }.keys.count())
        println("[2]")
        println(count.filter { it.value == 2 }.keys.count())
        println("[>2]")
        println(count.filter { it.value > 2 })

        part1Tiles.forEachIndexed { index, tile ->
            val noConnect = mutableListOf<Pair<Int, Int>>()
            tile.forEach { border ->
                if (count[border.first]!! == 1) {
                    noConnect.add(border)
                }
            }
            if (noConnect.size == 2) {
                println(tileNumbers[index])
                println(noConnect)
            }
        }
    }

    private fun readInput() {
        var i = 0
        var th = 0
        var up = ""
        var down = ""
        var left = ""
        var right = ""
        File("/Users/ampos/Documents/dota_is_down/LeetCode/leetcode-kotlin/src/advent2020/Day20-Real.input").forEachLine { line ->
            // 1 tile use 12 lines
            when (i) {
                0 -> {
                    val tileNumber = line.substring(5, 9)
                    tileNumbers[th] = tileNumber.toInt()

                    up = ""
                    down = ""
                    left = ""
                    right = ""
                }
                1 -> {
                    up = line
                    left += line.first()
                    right += line.last()
                }
                in 2..9 -> {
                    left += line.first()
                    right += line.last()
                }
                10 -> {
                    down = line
                    left += line.first()
                    right += line.last()
                }
                11 -> {
                    if (line.isNotEmpty()) {
                        throw Exception("This line must be empty")
                    }

                    th += 1

                    val upBi = up.map { charToBinary(it) }.joinToString("")
                    val upPair = Pair(upBi.toInt(2), upBi.reversed().toInt(2))
                    val leftBi = left.map { charToBinary(it) }.joinToString("")
                    val leftPair = Pair(leftBi.toInt(2), leftBi.reversed().toInt(2))
                    val rightBi = right.map { charToBinary(it) }.joinToString("")
                    val rightPair = Pair(rightBi.toInt(2), rightBi.reversed().toInt(2))
                    val downBi = down.map { charToBinary(it) }.joinToString("")
                    val downPair = Pair(downBi.toInt(2), downBi.reversed().toInt(2))
                    part1Tiles.add(listOf(upPair, leftPair, rightPair, downPair))
                }
            }
            i = (i + 1).rem(12)
        }
    }

    private fun charToBinary(c: Char): Char {
        return when (c) {
            '.' -> '0'
            '#' -> '1'
            else -> throw Exception("charToBinary not support $c")
        }
    }

    private fun countDuplicateBorders() {
        part1Tiles.forEach { tile ->
            tile.forEach { border ->
                val b1 = border.first
                val b2 = border.second
                count[b1] = (count[b1] ?: 0) + 1
                count[b2] = (count[b2] ?: 0) + 1
            }
        }
    }
}

fun main() {
    val d = Day20()
    d.execute()
}
