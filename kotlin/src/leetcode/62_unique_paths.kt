import kotlin.test.assertEquals

class Solution62 {
    fun uniquePaths(m: Int, n: Int): Long {
        val min = minOf(m - 1, n - 1)
        val sum = (m - 1) + (n - 1)
        var result: Long = 1
        for (i in 1..min) {
            result = result * (sum - i + 1) / i
        }

        return result
    }
}

fun main() {
    val s = Solution62()
    assertEquals(s.uniquePaths(3, 2), 3)
    assertEquals(s.uniquePaths(7, 3), 28)
    assertEquals(s.uniquePaths(51, 9), 1916797311)
}