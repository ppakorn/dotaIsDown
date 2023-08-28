package leetcode

class Solution74 {
    fun searchMatrix(matrix: Array<IntArray>, target: Int): Boolean {
        val m = matrix.size
        val n = matrix[0].size

        var i = 0
        var j = m * n - 1
        while (i <= j) {
            val mid = (i + j) / 2
            val indexM = mid / n
            val indexN = mid % n
            val midValue = matrix[indexM][indexN]
            if (midValue == target) {
                return true
            } else if (midValue < target) {
                i = mid + 1
            } else {
                j = mid - 1
            }
        }

        return false
    }
}

fun main() {
    val s = Solution74()
    val input = transform2DIntArray("[[1,1]]")
    println(s.searchMatrix(input, 1))
}
