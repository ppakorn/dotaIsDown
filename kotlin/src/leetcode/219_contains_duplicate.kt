package leetcode

import kotlin.math.abs

class Solution219 {
    fun containsNearbyDuplicate(nums: IntArray, k: Int): Boolean {
        val mem = mutableMapOf<Int, Int>()
        nums.forEachIndexed { i, x ->
            val previous = mem[x]
            previous?.let {
                if (abs(it - i) <= k) {
                    return true
                }
            }
            mem[x] = i
        }
        return false
    }
}

fun main() {
    val s = Solution219()
    s.containsNearbyDuplicate(intArrayOf(1,2,3,1), 3)
}