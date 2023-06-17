package leetcode

import kotlin.math.max

class Solution45 {
    // คิดเป็น batch
    // จาก 0 โดดไปได้ถึง max = x เท่ากับ 1 batch
    // จาก 1-x โดดไปได้ถึง max = y เป็น 2 batch
    fun jump(nums: IntArray): Int {
        var max = nums[0]
        var i = 1
        var count = 0
        while (i < nums.size) {

            var newMax = max
            while (i < nums.size && i <= max) {
                newMax = max(newMax, i + nums[i])
                i += 1
            }

            max = newMax
            count += 1
        }
        return count
    }
}

fun main() {
    val s = Solution45()
    val nums = intArrayOf(7,0,9,6,9,6,1,7,9,0,1,2,9,0,3)
    println(s.jump(nums))
}