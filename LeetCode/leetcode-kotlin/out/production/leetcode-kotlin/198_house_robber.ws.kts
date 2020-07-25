class Solution {
    fun rob(nums: IntArray): Int {
        var max0 = 0
        var max1 = 0
        for (element in nums) {
            val new = maxOf(max1, max0 + element)
            max0 = max1
            max1 = new
        }
        return max1
    }

    fun rob2(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        if (nums.size == 1) return nums[0]

        val c = IntArray(nums.size)
        c[0] = nums[0]
        c[1] = nums[1]
        var max: Int = maxOf(c[0], c[1])
        if (nums.size == 2) return max

        c[2] = c[0] + nums[2]
        max = maxOf(max, c[2])
        for (i in 3 until nums.size) {
            c[i] = maxOf(c[i-2], c[i-3]) + nums[i]
            max = maxOf(max, c[i])
        }
        return max
    }
}

val s = Solution213()
println(s.rob(intArrayOf(1, 2, 3, 1)))
println(s.rob(intArrayOf(2, 1, 3, 4)))
println(s.rob(intArrayOf(2, 7, 9, 3, 1)))
println(s.rob(intArrayOf(2, 8, 9, 5, 1)))
println(s.rob(intArrayOf()))
println(s.rob(intArrayOf(1)))
println(s.rob(intArrayOf(1, 2)))
println(s.rob(intArrayOf(2, 3, 4)))
println(s.rob(intArrayOf(1, 4, 2)))