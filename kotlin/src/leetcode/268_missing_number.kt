class Solution268 {
    fun missingNumber(nums: IntArray): Int {
        val sum = nums.sum()
        val expectedSum = nums.size * (nums.size + 1) / 2
        return expectedSum - sum
    }
}