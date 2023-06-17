package leetcode

class Solution189 {
    // step 1 reverse ทั้ง array
    // step 2 แบ่งตรงที่อยากให้มาเป็นตัวแรกสุด (แบ่งตรง index k หลัง reverse)
    // step 3 reverse ครึ่งซ้ายให้ตัวหน้ามาถูกต้อง ครึ่งขวาให้ลำดับถูกต้อง
    fun rotate(nums: IntArray, k: Int): Unit {
        val k2 = k % nums.size
        reverse(nums, 0, nums.size - 1)
        reverse(nums, 0, k2 - 1)
        reverse(nums, k2, nums.size - 1)
    }

    private fun reverse(nums: IntArray, startIndex: Int, endIndexInclusive: Int) {
        var i = startIndex
        var j = endIndexInclusive
        while (i < j) {
            val temp = nums[i]
            nums[i] = nums[j]
            nums[j] = temp

            i += 1
            j -= 1
        }
    }
}

fun main() {
    val s = Solution189()
    val nums = intArrayOf(1,2,3,4,5,6,7)
    s.rotate(nums, 3)
    println(nums.contentToString())
}