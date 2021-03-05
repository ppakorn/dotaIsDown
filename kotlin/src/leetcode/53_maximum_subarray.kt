import kotlin.test.assertEquals

class Solution53 {
    // Dynamic Programming O(n)
//    fun maxSubArray(nums: IntArray): Int {
//        if (nums.isEmpty()) return 0
//        var max = nums[0]
//        var sum = nums[0]
//        for (i in 1 until nums.size) {
//            val v = nums[i]
//            sum = maxOf(sum + v, v)
//            max = maxOf(sum, max)
//        }
//        return max
//    }

    // Divide and Conquer O(nlogn)
    // https://www.geeksforgeeks.org/maximum-subarray-sum-using-divide-and-conquer-algorithm/
    fun maxSubArray(nums: IntArray): Int {
        if (nums.size == 1) return nums[0]

        val mid = nums.size / 2
        val sliceLeft = nums.sliceArray(0 until mid)
        val sliceRight = nums.sliceArray(mid..nums.lastIndex)

        val maxLeft = maxSubArray(sliceLeft)
        val maxRight = maxSubArray(sliceRight)
        val maxMidLeft = maxFromRight(sliceLeft)
        val maxMidRight = maxFromLeft(sliceRight)
        return maxOf(maxLeft, maxRight, maxMidLeft + maxMidRight)
    }

    private fun maxFromRight(nums: IntArray): Int {
        var max = nums.last()
        var sum = nums.last()
        for (i in nums.lastIndex - 1 downTo 0) {
            sum += nums[i]
            max = maxOf(max, sum)
        }
        return max
    }

    private fun maxFromLeft(nums: IntArray): Int {
        var max = nums.first()
        var sum = nums.first()
        for (i in 1 until nums.size) {
            sum += nums[i]
            max = maxOf(max, sum)
        }
        return max
    }
}

fun main() {
    val s = Solution53()
    assertEquals(6, s.maxSubArray(intArrayOf(-2,1,-3,4,-1,2,1,-5,4)))
    assertEquals(-1, s.maxSubArray(intArrayOf(-1,-2,-3,-4)))
    assertEquals(1, s.maxSubArray(intArrayOf(-2,1,-3,-4,-1,-2,1,-5,-4)))
}