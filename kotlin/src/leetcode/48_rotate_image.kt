class Solution48 {
    fun rotate(matrix: Array<IntArray>): Unit {
        val len = matrix.size
        for (i in 0 until len / 2) {
            for (j in i until len - i - 1) {
                val point1 = Pair(i, j)
                val point1Value = matrix[point1.first][point1.second]
                val point2 = Pair(j, len - i - 1)
                val point3 = Pair(len - i - 1, len - j - 1)
                val point4 = Pair(len - j - 1, i)
                matrix[point1.first][point1.second] = matrix[point4.first][point4.second]
                matrix[point4.first][point4.second] = matrix[point3.first][point3.second]
                matrix[point3.first][point3.second] = matrix[point2.first][point2.second]
                matrix[point2.first][point2.second] = point1Value
            }
        }
    }
}

fun main() {
    val s = Solution48()
    val m5 = arrayOf(
        intArrayOf(1, 2, 3, 4, 5, 6),
        intArrayOf(7, 8, 9, 10, 11, 12),
        intArrayOf(13, 14, 15, 16, 17, 18),
        intArrayOf(19, 20, 21, 22, 23, 24),
        intArrayOf(25, 26, 27, 28, 29, 30),
        intArrayOf(31, 32, 33, 34, 35, 36)
    )
    print(m5)
    println("===============")
    s.rotate(m5)
    print(m5)
}

private fun print(matrix: Array<IntArray>) {
    for (row in matrix) {
        for (n in row) {
            print("$n ")
        }
        println()
    }
}