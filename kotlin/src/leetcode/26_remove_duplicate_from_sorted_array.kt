class Solution26 {
    fun removeDuplicates(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        var targetIndex = -1
        var previous = nums[0]
        var count = 1
        for (i in 1 until nums.size) {
            if (nums[i] > previous) {
                previous = nums[i]
                count += 1
                if (targetIndex > -1) {
                    // swap then targetIndex++
                    val temp = nums[i]
                    nums[i] = nums[targetIndex]
                    nums[targetIndex] = temp
                    targetIndex += 1
                }
            } else if (nums[i] == previous && targetIndex == -1) {
                targetIndex = i
            }
        }
        return count
    }
}

fun main() {
    val s = Solution26()
    val nums = intArrayOf(1,2,2,2,3,5,7,7,9)
    println(s.removeDuplicates(nums))
    println(nums.contentToString())
}