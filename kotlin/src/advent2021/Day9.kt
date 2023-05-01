package advent2021

import java.io.File

class Day9 {
    fun execute() {
        val board = mutableListOf<List<Int>>()
        File("/Users/ampos/Documents/dota_is_down/kotlin/src/advent2021/Day9.input").forEachLine { line ->
            val a = line.toCharArray().toList().map { it - '0' }
            board.add(a)
        }

        findBasins(board)
    }

    private fun countLow(board: List<List<Int>>): Int {
        val lastIndexI = board.size - 1
        val lastIndexJ = board[0].size - 1
        var count = 0
        board.forEachIndexed outer@{ i, row ->
            row.forEachIndexed inner@{ j, x ->
                if (
                    (j > 0 && x >= row[j - 1]) ||
                    (j < lastIndexJ && x >= row[j + 1]) ||
                    (i > 0 && x >= board[i - 1][j]) ||
                    (i < lastIndexI && x >= board[i + 1][j])
                ) {
                    return@inner
                }

                count += (x + 1)
            }
        }
        return count
    }

    private fun findBasins(board: List<List<Int>>) {
        val lows = findLowPoints(board)
        val basinSizes = mutableListOf<Int>()
        lows.forEach {
            val checked = mutableSetOf<Pair<Int, Int>>()
            findBasinSizeRecur(board, it, checked)
            basinSizes.add(checked.size)
        }

        val sorted = basinSizes.sortedDescending()
        println(sorted[0] * sorted[1] * sorted[2])
    }

    private fun findLowPoints(board: List<List<Int>>): List<Pair<Int, Int>> {
        val lastIndexI = board.size - 1
        val lastIndexJ = board[0].size - 1
        val lows = mutableListOf<Pair<Int, Int>>()
        board.forEachIndexed outer@{ i, row ->
            row.forEachIndexed inner@{ j, x ->
                if (
                    (j > 0 && x >= row[j - 1]) ||
                    (j < lastIndexJ && x >= row[j + 1]) ||
                    (i > 0 && x >= board[i - 1][j]) ||
                    (i < lastIndexI && x >= board[i + 1][j])
                ) {
                    return@inner
                }

                lows.add(Pair(i, j))
            }
        }
        return lows
    }

    private fun findBasinSizeRecur(board: List<List<Int>>, point: Pair<Int, Int>, checked: MutableSet<Pair<Int, Int>>) {
        if (board[point.first][point.second] == 9) return

        checked.add(point)

        val up = Pair(point.first - 1, point.second)
        if (up.first >= 0 && !checked.contains(up))
            findBasinSizeRecur(board, up, checked)

        val left = Pair(point.first, point.second - 1)
        if (left.second >= 0 && !checked.contains(left))
            findBasinSizeRecur(board, left, checked)

        val down = Pair(point.first + 1, point.second)
        if (down.first < board.size && !checked.contains(down))
            findBasinSizeRecur(board, down, checked)

        val right = Pair(point.first, point.second + 1)
        if (right.second < board[0].size && !checked.contains(right))
            findBasinSizeRecur(board, right, checked)
    }
}

fun main() {
    val d = Day9()
    d.execute()
}
