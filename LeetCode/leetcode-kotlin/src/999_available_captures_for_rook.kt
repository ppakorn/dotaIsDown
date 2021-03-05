class Solution999 {
    fun numRookCaptures(board: Array<CharArray>): Int {
        val (row, column) = findRook(board)

        var count = 0

        // right
        var i = column + 1
        loop@ while (i < board.size) {
            when (board[row][i]) {
                'p' -> {
                    count += 1
                    break@loop
                }
                'B' -> {
                    break@loop
                }
            }
            i += 1
        }

        // left
        i = column - 1
        loop@ while (i >= 0) {
            when (board[row][i]) {
                'p' -> {
                    count += 1
                    break@loop
                }
                'B' -> {
                    break@loop
                }
            }
            i -= 1
        }

        // up
        i = row - 1
        loop@ while (i >= 0) {
            when (board[i][column]) {
                'p' -> {
                    count += 1
                    break@loop
                }
                'B' -> {
                    break@loop
                }
            }
            i -= 1
        }

        // down
        i = row + 1
        loop@ while (i < board.size) {
            when (board[i][column]) {
                'p' -> {
                    count += 1
                    break@loop
                }
                'B' -> {
                    break@loop
                }
            }
            i += 1
        }

        return count
    }

    private fun findRook(board: Array<CharArray>): Pair<Int, Int> {
        board.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { columnIndex, char ->
                if (char == 'R') {
                    return Pair(rowIndex, columnIndex)
                }
            }
        }
        return Pair(-1, -1)
    }
}