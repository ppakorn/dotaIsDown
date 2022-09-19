package advent2021

import java.io.File

class Day11 {
    fun execute() {
        val board = mutableListOf<MutableList<Int>>()
        File("/Users/ampos/Documents/dota_is_down/kotlin/src/advent2021/Day11.input").forEachLine { line ->
            val a = line.toCharArray().map { it - '0' }.toMutableList()
            board.add(a)
        }

        (1..1000).forEach { i ->
            if (oneStep(board) == 100) {
                println(i)
            }
        }
        printBoard(board)
    }

    private fun printBoard(board: List<List<Int>>) {
        board.forEach { row ->
            row.forEach { x ->
                print("$x")
            }
            println()
        }
    }

    // Return flash times and mutate board param
    private fun oneStep(board: MutableList<MutableList<Int>>): Int {
        addOne(board)
        val flashPoints = findInitialFlashPoints(board)
        flashPoints.forEach {
            flashRecur(board, it)
        }
        return countAndRefreshFlash(board)
    }

    private fun addOne(board: MutableList<MutableList<Int>>) {
        board.forEachIndexed { i, row ->
            row.forEachIndexed { j, x ->
                board[i][j] = x + 1
            }
        }
    }

    private fun findInitialFlashPoints(board: List<List<Int>>): MutableList<Pair<Int, Int>> {
        val flashPoints = mutableListOf<Pair<Int, Int>>()
        board.forEachIndexed { i, row ->
            row.forEachIndexed { j, x ->
                if (x > 9) {
                    flashPoints.add(Pair(i, j))
                }
            }
        }
        return flashPoints
    }

    private fun flashRecur(board: MutableList<MutableList<Int>>, flashPoint: Pair<Int, Int>) {
        val i = flashPoint.first
        val j = flashPoint.second
        val limit = board.size - 1

        // Top-Left
        if (i > 0 && j > 0 && board[i - 1][j - 1] < 10) {
            board[i - 1][j - 1] += 1
            if (board[i - 1][j - 1] > 9) {
                flashRecur(board, Pair(i - 1, j - 1))
            }
        }

        // Up
        if (i > 0 && board[i - 1][j] < 10) {
            board[i - 1][j] += 1
            if (board[i - 1][j] > 9) {
                flashRecur(board, Pair(i - 1, j))
            }
        }

        // Top-Right
        if (i > 0 && j < limit && board[i - 1][j + 1] < 10) {
            board[i - 1][j + 1] += 1
            if (board[i - 1][j + 1] > 9) {
                flashRecur(board, Pair(i - 1, j + 1))
            }
        }

        // Left
        if (j > 0 && board[i][j - 1] < 10) {
            board[i][j - 1] += 1
            if (board[i][j - 1] > 9) {
                flashRecur(board, Pair(i, j - 1))
            }
        }

        // Right
        if (j < limit && board[i][j + 1] < 10) {
            board[i][j + 1] += 1
            if (board[i][j + 1] > 9) {
                flashRecur(board, Pair(i, j + 1))
            }
        }

        // Bottom-Left
        if (i < limit && j > 0 && board[i + 1][j - 1] < 10) {
            board[i + 1][j - 1] += 1
            if (board[i + 1][j - 1] > 9) {
                flashRecur(board, Pair(i + 1, j - 1))
            }
        }

        // Down
        if (i < limit && board[i + 1][j] < 10) {
            board[i + 1][j] += 1
            if (board[i + 1][j] > 9) {
                flashRecur(board, Pair(i + 1, j))
            }
        }

        // Bottom-Right
        if (i < limit && j < limit && board[i + 1][j + 1] < 10) {
            board[i + 1][j + 1] += 1
            if (board[i + 1][j + 1] > 9) {
                flashRecur(board, Pair(i + 1, j + 1))
            }
        }
    }

    private fun countAndRefreshFlash(board: MutableList<MutableList<Int>>): Int {
        var count = 0
        board.forEachIndexed { i, row ->
            row.forEachIndexed { j, x ->
                if (x > 9) {
                    count += 1
                    board[i][j] = 0
                }
            }
        }
        return count
    }
}

fun main() {
    val d = Day11()
    d.execute()
}