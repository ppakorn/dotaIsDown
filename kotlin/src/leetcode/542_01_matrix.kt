package leetcode

import java.util.*

class Solution542 {

    private val directions = listOf(
        listOf(-1, 0),
        listOf(0, -1),
        listOf(1, 0),
        listOf(0, 1)
    )


    fun updateMatrix(mat: Array<IntArray>): Array<IntArray> {
        val m = mat.size
        val n = mat[0].size
        val q: Queue<Pair<Int, Int>> = LinkedList()

        // Add 0 to queue
        // Change 1 to -1
        mat.forEachIndexed { i, row ->
            row.forEachIndexed { j, x ->
                if (x == 1) {
                    mat[i][j] = -1
                } else if (x == 0) {
                    q.add(Pair(i, j))
                }
            }
        }

        while (q.isNotEmpty()) {
            val p = q.remove()
            val i = p.first
            val j = p.second
            directions.forEach { d ->
                val newI = i + d[0]
                val newJ = j + d[1]
                if (!isValid(m, n, newI, newJ)) {
                    return@forEach
                }

                if (mat[newI][newJ] == -1) {
                    mat[newI][newJ] = mat[i][j] + 1
                    q.add(Pair(newI, newJ))
                }
            }
        }

        return mat
    }

    private fun isValid(m: Int, n: Int, i: Int, j: Int): Boolean {
        return i >= 0 && j >= 0 && i < m && j < n
    }
}

fun main() {
    val s = Solution542()
    val input = transform2DIntArray("[[0,0,0],[0,1,0],[0,0,0]]")
    s.updateMatrix(input).forEach { println(it.contentToString()) }
}