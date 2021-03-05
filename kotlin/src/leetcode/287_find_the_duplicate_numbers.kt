import kotlin.test.assertEquals

class Solution287 {
    fun findDuplicate(nums: IntArray): Int {
        return findDuplicateUsingLinkedList(nums)
//        return findDuplicate(nums, 1, nums.size - 1)
    }

    private fun findDuplicate(nums: IntArray, low: Int, high: Int): Int {
        if (low >= high) return low

        val mid = low + (high - low) / 2
        var countLow = 0
        var countHigh = 0
        nums.forEach {
            if (it < low || it > high) {
                return@forEach
            } else if (it <= mid) {
                countLow += 1
            } else {
                countHigh += 1
            }
        }

        return if (countLow > countHigh) {
            findDuplicate(nums, low, mid)
        } else {
            findDuplicate(nums, mid + 1, high)
        }
    }

    private fun findDuplicateUsingLinkedList(nums: IntArray): Int {
        var slow = nums[0]
        var fast = nums[nums[0]]
        while (slow != fast) {
            slow = nums[slow]
            fast = nums[nums[fast]]
        }

        fast = 0
        while (slow != fast) {
            slow = nums[slow]
            fast = nums[fast]
        }

        return fast
    }
}

fun main() {
    val s = Solution287()
    println(s.findDuplicate(intArrayOf(1,3,4,2,2)))
    println(s.findDuplicate(intArrayOf(1,3,5,7,8,3,2,4,3)))
}