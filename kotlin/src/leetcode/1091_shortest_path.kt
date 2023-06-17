package leetcode

import java.util.*

class Solution1091 {
    private val directions = listOf(
        Pair(-1, -1),
        Pair(-1, 0),
        Pair(-1, 1),
        Pair(0, -1),
        Pair(0, 1),
        Pair(1, -1),
        Pair(1, 0),
        Pair(1, 1)
    )

    fun shortestPathBinaryMatrix(grid: Array<IntArray>): Int {
        if (grid[0][0] == 1) {
            return -1
        }

        val maxRow = grid.size - 1
        val maxColumn = grid[0].size - 1
        if (maxRow == 0 && maxColumn == 0) {
            return 1
        }

        replace1ToMinus1(grid)
        grid[0][0] = 1

        val q: Queue<Pair<Int, Int>> = LinkedList()
        q.add(Pair(0, 0))
        while (q.isNotEmpty()) {
            val point = q.remove()
            val i = point.first
            val j = point.second
            val v = grid[i][j]

            directions.forEach { p ->
                val newI = i + p.first
                val newJ = j + p.second

                if (newI in 0..maxRow && newJ in 0 .. maxColumn && grid[newI][newJ] == 0) {
                    if (newI == maxRow && newJ == maxColumn) {
                        return v + 1
                    }
                    grid[newI][newJ] = v + 1
                    q.add(Pair(newI, newJ))
                }
            }
        }

        return -1
    }

    private fun replace1ToMinus1(grid: Array<IntArray>) {
        grid.forEachIndexed { i, row ->
            row.forEachIndexed { j, v ->
                if (v == 1) {
                    grid[i][j] = -1
                }
            }
        }
    }

    fun transformInput(str: String): Array<IntArray> {
        return str
            .removePrefix("[")
            .removeSuffix("]")
            .split("],")
            .map { a ->
                val aa = a.removePrefix("[").removeSuffix("]")
                if (aa.isEmpty()) {
                    emptyList<Int>().toIntArray()
                } else {
                    aa.split(",").map { it.toInt() }.toIntArray()
                }
            }
            .toTypedArray()
    }
}

fun main() {
    val s = Solution1091()
    val input = s.transformInput("[[0,0,0],[1,1,0],[1,1,0]]")
    println(s.shortestPathBinaryMatrix(input))
}