package leetcode

import kotlin.math.min

class Solution209 {
    fun minSubArrayLen(target: Int, nums: IntArray): Int {
        var windowStart = 0
        var sum = 0
        var minWindowSize: Int? = null

        for (windowEnd in nums.indices) {
            sum += nums[windowEnd]

            while (sum >= target) {
                minWindowSize = min(minWindowSize ?: Int.MAX_VALUE, windowEnd - windowStart + 1)
                sum -= nums[windowStart]
                windowStart += 1
            }
        }

        return minWindowSize ?: 0
    }
}

fun main() {
    val s = Solution209()
    println(s.minSubArrayLen(7, intArrayOf(2,3,1,2,4,3)))
    println(s.minSubArrayLen(11, intArrayOf(1,2,3,4,5)))

}