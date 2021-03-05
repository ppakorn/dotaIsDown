class Solution1001 {
    fun gridIllumination(N: Int, lamps: Array<IntArray>, queries: Array<IntArray>): IntArray {
        val horizontal = mutableMapOf<Int, MutableList<Int>>()
        val vertical = mutableMapOf<Int, Int>()
        val diagonalR = mutableMapOf<Int, Int>()
        val diagonalL = mutableMapOf<Int, Int>()
        lamps.forEach { lamp ->
            if (horizontal[lamp[0]] == null) {
                horizontal[lamp[0]] = mutableListOf(lamp[1])
            } else {
                horizontal[lamp[0]]!!.add(lamp[1])
            }
            vertical[lamp[1]] = (vertical[lamp[1]] ?: 0).inc()
            val dr = dr(row = lamp[0], column = lamp[1])
            diagonalR[dr] = (diagonalR[dr] ?: 0).inc()
            val dl = dl(N, row = lamp[0], column = lamp[1])
            diagonalL[dl] = (diagonalL[dl] ?: 0).inc()
        }

        val result = IntArray(queries.size) { 0 }
        queries.forEachIndexed { index, point ->
            if (!horizontal[point[0]].isNullOrEmpty()
                || (vertical[point[1]]?.let { it > 0 } == true)
                || (diagonalR[dr(row = point[0], column = point[1])]?.let { it > 0 } == true)
                || (diagonalL[dl(N, row = point[0], column = point[1])]?.let { it > 0 } == true)
            ) {
                result[index] = 1
            }

            // Turn off
            val rows = listOf(point[0] - 1, point[0], point[0] + 1)
            val columns = listOf(point[1] - 1, point[1], point[1] + 1)
            rows.forEach { row ->
                if (horizontal[row].isNullOrEmpty())
                    return@forEach

                val columnsToOff = horizontal[row]!!
                    .filter { columns.contains(it) }
                if (columnsToOff.isEmpty())
                    return@forEach

                columnsToOff.forEach { column ->
                    horizontal[row]!!.remove(column)
                    vertical[column] = vertical[column]!! - 1
                    val dr = dr(row, column)
                    diagonalR[dr] = diagonalR[dr]!! - 1
                    val dl = dl(N, row, column)
                    diagonalL[dl] = diagonalL[dl]!! - 1
                }
            }

        }

        return result
    }

    private fun dr(row: Int, column: Int): Int {
        return column - row
    }

    private fun dl(N: Int, row: Int, column: Int): Int {
        return row + column - N + 1
    }
}

fun main() {
    val s = Solution1001()
    println(s.gridIllumination(
        5,
        arrayOf(
            intArrayOf(0,0),
            intArrayOf(4,4)
        ),
        arrayOf(
            intArrayOf(1,1),
            intArrayOf(1,1)
        )
    ).contentToString())
    println(s.gridIllumination(
        5,
        arrayOf(
            intArrayOf(0,0),
            intArrayOf(0,4)
        ),
        arrayOf(
            intArrayOf(0,4),
            intArrayOf(0,1),
            intArrayOf(1,4)
        )
    ).contentToString())
}