class Solution980 {
    fun uniquePathsIII(grid: Array<IntArray>): Int {
        val count0 = grid.sumBy { row -> row.count { it == 0 } }
        var start = Pair(0, 0)
        grid.forEachIndexed { i, row ->
            row.forEachIndexed { j, it ->
                if (it == 1) {
                    start = Pair(i, j)
                }
            }
        }
        return recursive(grid, start, 0, count0)
    }

    private fun recursive(grid: Array<IntArray>, start: Pair<Int, Int>, count: Int, count0: Int): Int {
        if (grid[start.first][start.second] == 2 && count == count0 + 1) {
            return 1
        }

        val newGrid = grid.map { it.clone() }.toTypedArray()
        newGrid[start.first][start.second] = 1

        var resultCount = 0
        // down
        if (start.first < grid.size - 1 &&
            (newGrid[start.first + 1][start.second] == 0 || newGrid[start.first + 1][start.second] == 2)
        ) {
            resultCount += recursive(newGrid, Pair(start.first + 1, start.second), count + 1, count0)
        }
        // up
        if (start.first > 0 &&
            (newGrid[start.first - 1][start.second] == 0 || newGrid[start.first - 1][start.second] == 2)
        ) {
            resultCount += recursive(newGrid, Pair(start.first - 1, start.second), count + 1, count0)
        }
        // left
        if (start.second > 0 &&
            (newGrid[start.first][start.second - 1] == 0 || newGrid[start.first][start.second - 1] == 2)
        ) {
            resultCount += recursive(newGrid, Pair(start.first, start.second - 1), count + 1, count0)
        }
        // right
        if (start.second < grid[0].size - 1 &&
            (newGrid[start.first][start.second + 1] == 0 || newGrid[start.first][start.second + 1] == 2)
        ) {
            resultCount += recursive(newGrid, Pair(start.first, start.second + 1), count + 1, count0)
        }
        return resultCount
    }
}

fun main() {
    val s = Solution980()
    val grid1 = arrayOf(
        intArrayOf(1,0,0,0),
        intArrayOf(0,0,0,0),
        intArrayOf(0,0,2,-1)
    )
    val grid2 = arrayOf(
        intArrayOf(1,0,0,0),
        intArrayOf(0,0,0,0),
        intArrayOf(0,0,0,2)
    )
    println(s.uniquePathsIII(grid1))
    println(s.uniquePathsIII(grid2))
}