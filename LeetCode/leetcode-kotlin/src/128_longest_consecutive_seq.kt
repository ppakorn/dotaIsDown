import kotlin.test.assertEquals

class Solution {
    fun longestConsecutive(nums: IntArray): Int {
        val n = nums.distinct()
        val map = mutableMapOf<Int, Int>()
        for (x in n) {
            val lCount = map[x - 1] ?: 0
            val rimL = x - lCount
            val rCount = map[x + 1] ?: 0
            val rimR = x + rCount

            val new = lCount + rCount + 1
            map[rimL] = new
            map[rimR] = new
        }


        return map.maxBy { it.value }?.value ?: 0
    }
}

fun main() {
    val s = Solution()
    assertEquals(s.longestConsecutive(intArrayOf(100, 4, 200, 1, 3, 2, 5)), 5)
    assertEquals(s.longestConsecutive(intArrayOf(100, 4, 200, 2, 1, 3, 2, 5)), 5)
    assertEquals(s.longestConsecutive(intArrayOf()), 0)
    assertEquals(s.longestConsecutive(intArrayOf(100, 100, 100)), 1)
    assertEquals(s.longestConsecutive(intArrayOf(100, 4)), 1)
    assertEquals(s.longestConsecutive(intArrayOf(-2, 0, 100, 4, 200, 2, 1, 3, 2, 5)), 6)
    assertEquals(s.longestConsecutive(intArrayOf(0, -2, 100, 4, -1, 200, 2, 1, 3, 2, 5)), 8)
}