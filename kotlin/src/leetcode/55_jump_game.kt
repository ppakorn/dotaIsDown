package leetcode

import kotlin.math.max

class Solution55 {
    // วิ่งเก็บไปเรื่อยๆ ว่าโดดไปได้ไกลถึงไหน
    fun canJump(nums: IntArray): Boolean {
        var max = 0
        var i = 0
        while (i <= max) {
            max = max(max, i + nums[i])
            if (max >= nums.size - 1) {
                return true
            }
            i += 1
        }
        return false
    }
}

fun main() {
//    val s = Solution55()
//    val nums = intArrayOf(3,2,1,0,4)
//    println(s.canJump(nums))
//    val set = setOf(1, 2, 3)
//    println(set.)
}