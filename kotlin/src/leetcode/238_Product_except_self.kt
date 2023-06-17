package leetcode

class Solution238 {
    fun productExceptSelf(nums: IntArray): IntArray {
        val result = IntArray(nums.size) { 1 }
        result[0] = nums[0]
        for (i in 1 until nums.size) {
            result[i] = result[i - 1] * nums[i]
        }

        var p = 1
        for (i in nums.size - 1 downTo 1) {
            result[i] = p * result[i - 1]
            p *= nums[i]
        }
        result[0] = p

        return result
    }
}

fun main() {
    val s = Solution238()
    val input = intArrayOf(-1,1,0,-3,3)
    println(s.productExceptSelf(input).contentToString())
}