import kotlin.test.assertEquals

class Solution213 {
    private fun rob1(nums: IntArray): Int {
        var max0 = 0
        var max1 = 0
        for (element in nums) {
            val new = maxOf(max1, max0 + element)
            max0 = max1
            max1 = new
        }
        return max1
    }

    fun rob(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        if (nums.size == 1) return nums[0]

        val slice1 = nums.sliceArray(0 until nums.size - 1)
        val r1 = rob1(slice1)
        val slice2 = nums.sliceArray(1 until nums.size)
        val r2 = rob1(slice2)
        return maxOf(r1, r2)
    }
}

fun main() {
    val s = Solution213()
    assertEquals(s.rob(intArrayOf(2, 3, 2)),  3)
    assertEquals(s.rob(intArrayOf(1, 2, 3, 1)),  4)
    assertEquals(s.rob(intArrayOf(2, 1, 3, 4)),  5)
    assertEquals(s.rob(intArrayOf(2, 7, 9, 3, 1)),  11)
    assertEquals(s.rob(intArrayOf(2, 8, 9, 5, 1)),  13)
    assertEquals(s.rob(intArrayOf()),  0)
    assertEquals(s.rob(intArrayOf(1)),  1)
    assertEquals(s.rob(intArrayOf(1, 2)),  2)
    assertEquals(s.rob(intArrayOf(2, 3, 4)),  4)
    assertEquals(s.rob(intArrayOf(1, 4, 2)),  4)
    assertEquals(s.rob(intArrayOf(1, 1)),  1)
    assertEquals(s.rob(intArrayOf(1, 1, 1, 2)) ,  3)
    assertEquals(s.rob(intArrayOf(2, 2, 4, 3, 2, 5)), 10)
}