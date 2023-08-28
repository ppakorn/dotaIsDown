package leetcode

class Solution35 {
    fun searchInsert(nums: IntArray, target: Int): Int {
        var i = 0
        var j = nums.size
        while(i < j) {
            val mid = (i + j) / 2
            if (nums[mid] == target) {
                return mid
            } else if (nums[mid] < target) {
                i = mid + 1
            } else {
                j = mid
            }
        }
        return i
    }
}

fun main() {
    val s = Solution35()
    println(s.searchInsert(intArrayOf(1,3,5,6), 5))
}