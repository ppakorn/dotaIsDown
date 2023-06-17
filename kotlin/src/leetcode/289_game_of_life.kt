package leetcode

class Solution289 {
    // Follow up question:
    // In this question, we represent the board using a 2D array.
    // In principle, the board is infinite,
    // which would cause problems when the active area encroaches upon the border of the array
    // (i.e., live cells reach the border). How would you address these problems?

    // Answer:
    // เก็บจุดที่เป็น 1 แทน ถ้าจะ access เร็วอาจจะ encode เป็น Set<String> เก็บ [0][0] เป็น 0,0
    // ถ้า set contain แปลว่าจุดนั้นเป็น 1

    private val neighbors = listOf(
        listOf(-1, -1), listOf(-1, 0), listOf(-1, 1),
        listOf(0, -1), listOf(0, 1),
        listOf(1, -1), listOf(1, 0), listOf(1, 1)
    )

    fun gameOfLife(board: Array<IntArray>): Unit {
        // 2 = 1 -> 0
        // 3 = 0 -> 1
        for (i in board.indices) {
            for (j in board[i].indices) {
                val countLive = countLiveNeighbors(board, i, j)
                if (board[i][j] == 1 && (countLive < 2 || countLive > 3)) {
                    board[i][j] = 2
                } else if (board[i][j] == 0 && countLive == 3) {
                    board[i][j] = 3
                }
            }
        }

        for (i in board.indices) {
            for (j in board[i].indices) {
                board[i][j] %= 2
            }
        }
    }

    private fun countLiveNeighbors(board: Array<IntArray>, i: Int, j: Int): Int {
        var count = 0
        neighbors.forEach {
            val neighborI = i + it[0]
            val neighborJ = j + it[1]
            if (validPoint(board, neighborI, neighborJ) &&
                (board[neighborI][neighborJ] == 1 || board[neighborI][neighborJ] == 2)) {
                count += 1
            }
        }
        return count
    }

    private fun validPoint(board: Array<IntArray>, i: Int, j: Int): Boolean {
        return i in board.indices && j in board[0].indices
    }
}