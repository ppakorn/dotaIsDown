package leetcode

class Solution54 {
    private val directions = mapOf(
        0 to Pair(0, 1),
        1 to Pair(1, 0),
        2 to Pair(0, -1),
        3 to Pair(-1, 0)
    )
    fun spiralOrder(matrix: Array<IntArray>): List<Int> {
        val result = mutableListOf<Int>()
        val limit = listOf(
            mutableListOf(0, matrix.size - 1),
            mutableListOf(0, matrix[0].size - 1)
        )

        var row = 0
        var column = 0
        var direction = 0
        for (i in 0 until matrix.size * matrix[0].size) {
            println(i)
            result.add(matrix[row][column])

            var newRow = row + directions[direction]!!.first
            var newColumn = column + directions[direction]!!.second
            while (validLimit(limit) && !valid(newRow, newColumn, limit)) {
                adjustLimit(direction, limit)
                direction = (direction + 1) % 4
                newRow = row + directions[direction]!!.first
                newColumn = column + directions[direction]!!.second
            }

            row = newRow
            column = newColumn
        }

        return result
    }

    private fun validLimit(limit: List<List<Int>>): Boolean {
        return limit[0][0] <= limit[0][1] && limit[1][0] <= limit[1][1]
    }

    private fun valid(row: Int, column: Int, limit: List<List<Int>>): Boolean {
        return row in limit[0][0]..limit[0][1] && column in limit[1][0]..limit[1][1]
    }

    private fun adjustLimit(direction: Int, limit: List<MutableList<Int>>) {
        when (direction) {
            0 -> limit[0][0] += 1
            1 -> limit[1][1] -= 1
            2 -> limit[0][1] -= 1
            3 -> limit[1][0] += 1
        }
    }
}

fun main() {
    val s = Solution54()
    val matrix = transform2DIntArray("[[1,2,3],[4,5,6],[7,8,9]]")
    println(s.spiralOrder(matrix))
}