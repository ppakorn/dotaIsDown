package advent2021

import java.io.File
import java.util.*

class Day15 {
    fun execute() {
        val board = mutableListOf<List<Int>>()
        File("/Users/ampos/Documents/dota_is_down/kotlin/src/advent2021/Day15.input").forEachLine { line ->
            val a = line.toCharArray().map { it - '0' }
            board.add(a)
        }
        val bigBoard = build5x5Board(board)
//        printBoard(bigBoard)
        val size = bigBoard.size
        val risk = Array(size) { Array(size) {-1} }
        val heap = PriorityQueue<Triple<Int, Int, Int>> { a, b ->
            a.third - b.third
        }
        heap.add(Triple(0, 0, -1))
        dijkstra(bigBoard, risk, heap)
        println(risk[size - 1][size - 1])
//        printBoard(risk)
    }

    private fun calculateRisk1(board: List<List<Int>>, risk: Array<Array<Int>>) {
        val len = board.size
        for (i in 1 until len) {
            for (j in 0..i) {
                val row = i - j
                val column = j
                risk[row][column] = when (column) {
                    0 -> risk[row - 1][column] + board[row][column]
                    i -> risk[row][column - 1] + board[row][column]
                    else -> minOf(risk[row - 1][column], risk[row][column - 1]) + board[row][column]
                }
            }
        }

        for (i in 1 until len) {
            for (j in 0 until len - i) {
                val row = len - j - 1
                val column = i + j
                risk[row][column] = when (row) {
                    0 -> risk[row][column - 1] + board[row][column]
                    else -> minOf(risk[row - 1][column], risk[row][column - 1]) + board[row][column]
                }
            }
        }
    }

    private fun printBoard(board: Collection<Collection<Int>>) {
        board.forEach { row ->
            row.forEach { x ->
                print("$x")
            }
            println()
        }
    }

    private fun printBoard(board: Array<Array<Int>>) {
        board.forEach { row ->
            row.forEach { x ->
                print("$x ")
            }
            println()
        }
    }

    private fun build5x5Board(initial: List<List<Int>>): List<List<Int>> {
        val bigBoard = initial.map { it.toMutableList() }.toMutableList()

        // copy initial vertically
        (1..4).forEach { i ->
            val newBoard = initial.map { row ->
                row.map { x ->
                    if (x + i > 9) {
                        x + i - 9
                    } else {
                        x + i
                    }
                }.toMutableList()
            }
            bigBoard.addAll(newBoard)
        }

        val initialBigBoard = bigBoard.map { it.toList() }
        // copy board to right
        (1..4).forEach { i ->
            val newBoard = initialBigBoard.map { row ->
                row.map { x ->
                    if (x + i > 9) {
                        x + i - 9
                    } else {
                        x + i
                    }
                }
            }

            bigBoard.forEachIndexed { index, row ->
                row += newBoard[index]
            }
        }

        return bigBoard
    }

    private fun dijkstra(board: List<List<Int>>, risks: Array<Array<Int>>, heap: PriorityQueue<Triple<Int, Int, Int>>) {
        while (heap.isNotEmpty()) {
            val next = heap.remove()
            val i = next.first
            val j = next.second
            if (risks[i][j] > 0) {
                continue
            }

            val newRisk = next.third + board[i][j]
            risks[i][j] = newRisk

            // up
            if (i > 0 && risks[i - 1][j] < 0) {
                heap.add(Triple(i - 1, j, newRisk))
            }

            // left
            if (j > 0 && risks[i][j - 1] < 0) {
                heap.add(Triple(i, j - 1, newRisk))
            }

            // down
            if (i < board.size - 1 && risks[i + 1][j] < 0) {
                heap.add(Triple(i + 1, j, newRisk))
            }

            // right
            if (j < board.size - 1 && risks[i][j + 1] < 0) {
                heap.add(Triple(i, j + 1, newRisk))
            }
        }
    }
}

fun main() {
    val d = Day15()
    d.execute()
}