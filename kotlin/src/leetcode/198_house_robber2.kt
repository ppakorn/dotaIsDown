package leetcode

import kotlin.math.max

class Solution198v2 {
    private var mem = intArrayOf()
    fun rob(nums: IntArray): Int {
        mem = IntArray(nums.size) { -1 }
        return r(nums, nums.size - 1)
    }

    fun r(nums: IntArray, n: Int): Int {
        if (n < 0) return 0
        if (n == 0) return nums[0]
        if (mem[n] != -1) return mem[n]

        val r = max(
            r(nums, n - 1),
            r(nums, n - 2) + nums[n]
        )
        mem[n] = r
        return r
    }
}

fun main() {
    val s = Solution198v2()
    println(s.rob(intArrayOf(2,7,9,3,1)))
    println(s.rob(intArrayOf(2,7,9,6,1)))
}