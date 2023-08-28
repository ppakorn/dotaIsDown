package leetcode

import kotlin.math.max

class Solution300 {
    private val mem = mutableMapOf<Int, Int>(0 to 1)
    fun lengthOfLIS(nums: IntArray): Int {
        mem.clear()
        for (i in nums.indices) {
            f(nums, i)
        }
        return mem.values.max()!!
    }

    // หา longest โดยมี index n เป็นตัวสุดท้ายเสมอ
    // วิ่งหาตัวที่น้อยกว่าที่ยาวสุดแล้ว + 1
    // O(n2) จริงๆ น่าจะทำให้ O(nlogn) ได้
    private fun f(nums: IntArray, n: Int): Int {
        if (mem[n] != null) {
            return mem[n]!!
        }

        var max = 1
        var i = n - 1
        while (i >= 0) {
            if (nums[i] < nums[n]) {
                max = max(max, f(nums, i) + 1)
            }
            i -= 1
        }

        mem[n] = max
        return max
    }
}

fun main() {
    val s = Solution300()
//    println(s.lengthOfLIS(intArrayOf(10,9,2,5,3,7,101,18)))
//    println(s.lengthOfLIS(intArrayOf(0,1,0,3,2,3)))
    println(s.lengthOfLIS(intArrayOf(0)))
}