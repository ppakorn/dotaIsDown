package leetcode

class Solution1351 {
    fun countNegatives(grid: Array<IntArray>): Int {
        var count = 0
        var i = 0
        var j = grid[0].size - 1

        while (i < grid.size) {
            while (j >= 0 && grid[i][j] < 0) {
                count += grid.size - i
                j -= 1
            }
            i += 1
        }

        return count
    }
}

fun main() {
    val s = Solution1351()
    val input = transform2DIntArray("[[4,3,2,-1],[3,2,1,-1],[1,1,-1,-2],[-1,-1,-2,-3]]")
    println(s.countNegatives(input))
}