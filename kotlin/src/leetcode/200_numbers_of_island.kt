import kotlin.test.assertEquals

class Solution200 {
    private lateinit var grid: Array<CharArray>
    private var sizeX: Int = 0
    private var sizeY: Int = 0

    fun numIslands(grid: Array<CharArray>): Int {
        if (grid.isEmpty()) return 0

        this.grid = grid
        sizeX = grid.size
        sizeY = grid[0].size
        var n = 0

        for (x in 0 until sizeX) {
            for (y in 0 until sizeY) {
                if (grid[x][y] == '1') {
                    n += 1
                    markIslandArea(x, y)
                }
            }
        }
        return n
    }

    private fun markIslandArea(x: Int, y: Int) {
        grid[x][y] = '0'

        // LEFT
        if (y > 0 && grid[x][y - 1] == '1') markIslandArea(x, y - 1)

        // RIGHT
        if (y < sizeY - 1 && grid[x][y + 1] == '1') markIslandArea(x, y + 1)

        // DOWN
        if (x < sizeX - 1 && grid[x + 1][y] == '1') markIslandArea(x + 1, y)

        // UP
        if (x > 0 && grid[x - 1][y] == '1') markIslandArea(x - 1, y)
    }
}

fun main() {
    val s = Solution200()
    assertEquals(s.numIslands(arrayOf(charArrayOf('0'))), 0)
    assertEquals(s.numIslands(arrayOf(charArrayOf('1'))), 1)
    assertEquals(s.numIslands(arrayOf(charArrayOf())), 0)
    assertEquals(s.numIslands(emptyArray()), 0)
    val g1 = arrayOf(
        charArrayOf('1','1','1','1','0'),
        charArrayOf('1','1','0','1','0'),
        charArrayOf('1','1','0','0','0'),
        charArrayOf('0','0','0','0','0')
    )
    assertEquals(s.numIslands(g1), 1)
    val g2 = arrayOf(
        charArrayOf('0','1','1','0','0'),
        charArrayOf('1','1','0','0','0'),
        charArrayOf('0','0','1','0','0'),
        charArrayOf('0','0','0','1','1')
    )
    assertEquals(s.numIslands(g2), 3)
    val g3 = arrayOf(
        charArrayOf('0','1','0','0','1'),
        charArrayOf('1','1','0','1','1'),
        charArrayOf('0','0','1','0','0'),
        charArrayOf('1','1','1','1','1')
    )
    assertEquals(s.numIslands(g3), 3)
    val g4 = arrayOf(
        charArrayOf('1','0','1','1','1'),
        charArrayOf('1','0','1','0','1'),
        charArrayOf('1','1','1','0','1')
    )
    assertEquals(s.numIslands(g4), 1)
}