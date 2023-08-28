package leetcode

class Solution130 {
    private val directions = listOf(
        listOf(-1, 0),
        listOf(1, 0),
        listOf(0, 1),
        listOf(0, -1)
    )
    fun solve(board: Array<CharArray>): Unit {
        var protected = searchForOInBorder(board)
        protected = searchAdjacent(board, protected)

        board.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, c ->
                if (c == 'O' && !protected.contains(Pair(rowIndex, columnIndex))) {
                    board[rowIndex][columnIndex] = 'X'
                }
            }
        }
    }

    private fun searchForOInBorder(board: Array<CharArray>): Set<Pair<Int, Int>> {
        val m = board.size
        val n = board[0].size
        val protected = mutableSetOf<Pair<Int, Int>>()

        // First row
        board[0].forEachIndexed { index, c ->
            if (c == 'O')
                protected.add(Pair(0, index))
        }
        // Last row
        board[m - 1].forEachIndexed { index, c ->
            if (c == 'O')
                protected.add(Pair(m - 1, index))
        }
        // First and last column
        for (i in 1 until m - 1) {
            if (board[i][0] == 'O')
                protected.add(Pair(i, 0))
            if (board[i][n - 1] == 'O') {
                protected.add(Pair(i, n - 1))
            }
        }

        return protected
    }

    private fun searchAdjacent(board: Array<CharArray>, protected: Set<Pair<Int, Int>>): Set<Pair<Int, Int>> {
        val visited = mutableSetOf<Pair<Int, Int>>()
        protected.forEach { searchAdjacentRecur(board, it, visited) }
        return visited
    }

    private fun searchAdjacentRecur(board: Array<CharArray>, point: Pair<Int, Int>, visited: MutableSet<Pair<Int, Int>>) {
        if (visited.contains(point))
            return
        visited.add(point)

        val m = board.size
        val n = board[0].size
        directions.forEach {
            val newPoint = Pair(point.first + it[0], point.second + it[1])
            if (isValid(newPoint, m, n) && board[newPoint.first][newPoint.second] == 'O') {
                searchAdjacentRecur(board, newPoint, visited)
            }
        }
    }

    private fun isValid(point: Pair<Int, Int>, m: Int, n: Int): Boolean {
        return point.first in 0 until m && point.second in 0 until n
    }

    fun print(board: Array<CharArray>) {
        board.forEach {
            println(it.contentToString())
        }
    }
}

fun main() {
    val s = Solution130()
    val input = transform2DCharArray("""
        [["X","X","O","X"],["X","O","O","X"],["X","X","O","X"],["X","O","X","X"]]
    """.trimIndent())
    s.solve(input)
    s.print(input)
}

