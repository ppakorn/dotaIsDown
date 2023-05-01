import kotlin.test.assertEquals

class Solution169 {
    fun majorityElement(nums: IntArray): Int {
        var major = nums[0]
        var count = 0
        for (n in nums) {
            if (count == 0) {
                major = n
                count = 1
                continue
            }
            if (n == major) {
                count += 1
            } else {
                count -= 1
            }
        }
        return major
    }
}

fun main() {
    val s = Solution169()
    assertEquals(s.majorityElement(intArrayOf(3,2,3)), 3)
    assertEquals(s.majorityElement(intArrayOf(2,2,1,1,1,2,2)), 2)
}