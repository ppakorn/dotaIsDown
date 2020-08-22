import kotlin.test.assertEquals

class Solution41 {
    fun firstMissingPositive(nums: IntArray): Int {
        val x = nums.size + 1
        for (i in nums.indices) {
            if (nums[i] < 0 || nums[i] >= x) nums[i] = 0
        }

        nums.forEach { n ->
            if (n % x == 0) return@forEach
            val i = (n - 1) % x
            nums[i] += x
        }

        nums.forEachIndexed { i, n ->
            if (n < x) {
                return i + 1
            }
        }

        return x
    }
}

fun main() {
    val s = Solution41()
    assertEquals(s.firstMissingPositive(intArrayOf(1,2,0)), 3)
    assertEquals(s.firstMissingPositive(intArrayOf(3,4,-1,1)), 2)
    assertEquals(s.firstMissingPositive(intArrayOf(7,8,9,10,11,12)), 1)
    assertEquals(s.firstMissingPositive(intArrayOf()), 1)
    assertEquals(s.firstMissingPositive(intArrayOf(0)), 1)
    assertEquals(s.firstMissingPositive(intArrayOf(-1,-2,-3,0)), 1)
    assertEquals(s.firstMissingPositive(intArrayOf(10,9,8,7,6,5,4,3,2,1)), 11)
    assertEquals(s.firstMissingPositive(intArrayOf(10,9,8,7,6,5,4,3,2,1,0)), 11)
    assertEquals(s.firstMissingPositive(intArrayOf(1,2,3,4,5,5)), 6)
}