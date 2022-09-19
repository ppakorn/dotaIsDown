package advent2021

import java.io.File

class Day4 {
    fun execute() {
        var numbers: List<Int> = emptyList()
        var boardNumber = -1
        val boards: MutableList<List<List<IntArray>>> = mutableListOf()
        val numberToBoards: MutableMap<Int, MutableList<Triple<Int, Int, Int>>> = mutableMapOf()
        var currentBoard = mutableListOf<List<IntArray>>()
        var bingoBoard = mutableSetOf<Int>()
        File("/Users/ampos/Documents/dota_is_down/kotlin/src/advent2021/Day4.input").forEachLine { line ->
            if (numbers.isEmpty()) {
                numbers = line.split(",").map { it.toInt() }
                return@forEachLine
            }

            if (line.isBlank()) {
                if (boardNumber >= 0) {
                    boards.add(currentBoard)
                }
                boardNumber += 1
                currentBoard = mutableListOf()

                return@forEachLine
            }

            val a = line.split(" ").filter { it.isNotBlank() }.map { it.toInt() }
            currentBoard.add(a.map { intArrayOf(it, 0) })

            a.forEachIndexed { index, n ->
                if (numberToBoards[n] == null) {
                    numberToBoards[n] = mutableListOf()
                }
                numberToBoards[n]!!.add(Triple(boardNumber, currentBoard.size - 1, index))
            }
        }
        boards.add(currentBoard)

        numbers.forEach { i ->
            numberToBoards[i]?.forEach { (boardNumber, row, column) ->
                if (boardNumber in bingoBoard) return@forEach
                boards[boardNumber][row][column][1] = 1
                if (checkBoard(boards[boardNumber])) {
                    bingoBoard.add(boardNumber)
                    if (bingoBoard.count() == boards.count()) {
                        printResult(boards[boardNumber], i)
                    }
                }
            }
        }

    }

    private fun checkBoard(board: List<List<IntArray>>): Boolean {
        board.forEach { row ->
            val result = row.fold(0) { acc, ints -> acc + ints[1] }
            if (result == 5) {
                return true
            }
        }

        for (column in 0..4) {
            var result = 0
            for (row in 0..4) {
               result += board[row][column][1]
            }
            if (result == 5) {
                return true
            }
        }

        return false
    }

    private fun printResult(board: List<List<IntArray>>, lastNumber: Int) {
        var checked = 0
        var unchecked = 0
        board.forEach { row ->
            row.forEach { column ->
                if (column[1] == 0) {
                    unchecked += column[0]
                } else if (column[1] == 1) {
                    checked += column[0]
                }
            }
        }
        println(unchecked * lastNumber)
    }
}


fun main() {
    val d = Day4()
    d.execute()
}