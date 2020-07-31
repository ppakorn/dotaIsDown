class Solution1 {
    fun twoSum(nums: IntArray, target: Int): IntArray {
        val dict = mutableMapOf<Int, Int>()
        for (i in nums.indices) {
            val n = nums[i]
            if (dict.containsKey(target - n)) {
                return intArrayOf(dict[target - n]!!, i)
            }
            dict[n] = i
        }
        return intArrayOf()
    }
}

fun main() {
    val s = Solution1()
    println(s.twoSum(intArrayOf(2, 7, 11, 12), 9).contentToString())      // 0, 1
    println(s.twoSum(intArrayOf(2, 4, 12, 7), 9).contentToString())       // 0, 3
    println(s.twoSum(intArrayOf(2, 7, 11, 12, -4, -9), 2).contentToString())        // 2, 5
    println(s.twoSum(intArrayOf(-3, 4, -2, 5, 3, 12), 0).contentToString())         // 0, 4
}