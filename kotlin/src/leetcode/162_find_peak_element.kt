package leetcode

class Solution162 {
    fun findPeakElement(nums: IntArray): Int {
        var low = 0
        var high = nums.size

        while (low < high) {
            val mid = (low + high) / 2
            val left = greaterThanLeft(nums, mid)
            val right = greaterThanRight(nums, mid)
            if (left && right) {
                return mid
            } else if (left) {
                low = mid + 1
            } else {
                high = mid - 1
            }
        }
        return low
    }

    private fun greaterThanLeft(nums: IntArray, i: Int): Boolean {
        return  i == 0 || nums[i] > nums[i - 1]
    }

    private fun greaterThanRight(nums: IntArray, i: Int): Boolean {
        return i == nums.size - 1 || nums[i] > nums[i + 1]
    }
}

fun main() {
    val s = Solution162()
    println(s.findPeakElement(intArrayOf(1,2,1,3,5,6,4)))
}