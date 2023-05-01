package advent2021

import java.io.File
import java.lang.Integer.max
import java.lang.Integer.min

class Day5 {
    fun execute() {
        val size = 1000
        val board = Array(size) { IntArray(size) {0} }
        File("/Users/ampos/Documents/dota_is_down/kotlin/src/advent2021/Day5.input").forEachLine { line ->
            val a = line.split(" -> ")
            val start = a[0].split(",").map { it.toInt() }
            val end = a[1].split(",").map { it.toInt() }
            if (isHorizontal(start, end)) {
                for (i in min(start[0], end[0])..max(start[0], end[0])) {
                    board[start[1]][i] += 1
                }
            } else if (isVertical(start, end)) {
                for (i in min(start[1], end[1])..max(start[1], end[1])) {
                    board[i][start[0]] += 1
                }
            } else if (isPositiveSlope(start, end)) {
                val startPoint: List<Int>
                val endPoint: List<Int>
                if (min(start[0], end[0]) == start[0]) {
                    startPoint = start
                    endPoint = end
                } else {
                    startPoint = end
                    endPoint = start
                }
                for (i in 0..(endPoint[0] - startPoint[0])) {
                    board[startPoint[1] - i][startPoint[0] + i] += 1
                }
            } else if (isNegativeSlope(start, end)) {
                val startPoint: List<Int>
                val endPoint: List<Int>
                if (min(start[0], end[0]) == start[0]) {
                    startPoint = start
                    endPoint = end
                } else {
                    startPoint = end
                    endPoint = start
                }
                for (i in 0..(endPoint[0] - startPoint[0])) {
                    board[startPoint[1] + i][startPoint[0] + i] += 1
                }
            }
        }
//        printBoard(board)
        printResult1(board)
    }

    private fun isHorizontal(start: List<Int>, end: List<Int>): Boolean {
        return start[1] == end[1]
    }

    private fun isVertical(start: List<Int>, end: List<Int>): Boolean {
        return start[0] == end[0]
    }

    private fun isPositiveSlope(start: List<Int>, end: List<Int>): Boolean {
        return start[0] - end[0] == end[1] - start[1]
    }

    private fun isNegativeSlope(start: List<Int>, end: List<Int>): Boolean {
        return start[0] - end[0] == start[1] - end[1]
    }

    private fun printBoard(board: Array<IntArray>) {
        board.forEach { row ->
            row.forEach { x ->
                print("$x ")
            }
            println()
        }
    }

    private fun printResult1(board: Array<IntArray>) {
        var count = 0
        board.forEach { row ->
            row.forEach { x ->
                if (x >= 2) {
                    count += 1
                }
            }
        }
        println(count)
    }
}

fun main() {
    val d = Day5()
    d.execute()
}