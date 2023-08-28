package leetcode

class Solution63 {
    fun uniquePathsWithObstacles(obstacleGrid: Array<IntArray>): Int {
        val mem = Array(obstacleGrid.size) { Array(obstacleGrid[0].size) { -1 } }

        // Fill first column
        var blocked = false
        for (i in obstacleGrid.indices) {
            mem[i][0] = if (blocked || obstacleGrid[i][0] == 1) {
                blocked = true
                0
            } else {
                1
            }
        }

        // Fill first row
        blocked = false
        for (i in obstacleGrid[0].indices) {
            mem[0][i] = if (blocked || obstacleGrid[0][i] == 1) {
                blocked = true
                0
            } else {
                1
            }
        }

        for (i in 1 until obstacleGrid.size) {
            for (j in 1 until obstacleGrid[0].size) {
                mem[i][j] = if (obstacleGrid[i][j] == 1) {
                    0
                } else {
                    mem[i - 1][j] + mem[i][j - 1]
                }
            }
        }

        return mem[mem.lastIndex][mem[0].lastIndex]
    }
}

fun main() {
    val s = Solution63()
    val input = transform2DIntArray("[[0,0,0],[0,1,0],[0,0,0]]")
    println(s.uniquePathsWithObstacles(input))
}