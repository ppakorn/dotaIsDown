package leetcode

class Solution36 {
    fun isValidSudoku(board: Array<CharArray>): Boolean {
        return checkVertical(board) && checkHorizontal(board) && check33(board)
    }

    private fun checkVertical(board: Array<CharArray>): Boolean {
        for (column in board.indices) {
            val mem = mutableListOf<Char>()
            for (row in board.indices) {
                if (board[row][column] != '.') {
                    mem.add(board[row][column])
                }
            }
            if (mem.toSet().size < mem.size) {
                return false
            }
        }
        return true
    }

    private fun checkHorizontal(board: Array<CharArray>): Boolean {
        board.forEach { row ->
            val mem = mutableListOf<Char>()
            row.forEach {
                if (it != '.') {
                    mem.add(it)
                }
            }
            if (mem.toSet().size < mem.size) {
                return false
            }
        }
        return true
    }

    private fun check33(board: Array<CharArray>): Boolean {
        for (row in 0..6 step 3) {
            for (column in 0..6 step 3) {
                val mem = mutableListOf<Char>()
                for (i in 0 until 3) {
                    for (j in 0 until 3) {
                        if (board[row + i][column + j] != '.') {
                            mem.add(board[row + i][column + j])
                        }
                    }
                }
                if (mem.toSet().size < mem.size) {
                    return false
                }
            }
        }
        return true
    }
}

fun main() {
    val s = Solution36()
    val input = transform2DCharArray("""[["8","3",".",".","7",".",".",".","."]
,["6",".",".","1","9","5",".",".","."]
,[".","9","8",".",".",".",".","6","."]
,["8",".",".",".","6",".",".",".","3"]
,["4",".",".","8",".","3",".",".","1"]
,["7",".",".",".","2",".",".",".","6"]
,[".","6",".",".",".",".","2","8","."]
,[".",".",".","4","1","9",".",".","5"]
,[".",".",".",".","8",".",".","7","9"]]""")
    println(s.isValidSudoku(input))
}