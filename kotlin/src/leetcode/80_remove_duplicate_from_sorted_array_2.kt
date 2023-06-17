package leetcode

class Solution80 {
    // See Solution26
    // i คือ index สุดท้ายของ array ที่จะเอา
    // j คือ ไล่เช็ค
    fun removeDuplicates(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        var i = 1
        var j = 2
        while (j < nums.size) {
            if (nums[j] > nums[i]
                || (nums[j] == nums[i] && nums[i] > nums[i - 1])
            ) {
                // ถ้ามากกว่าหรือเท่ากับครั้งแรกเอามาเติมลงช่อง i ขยับ i
                // ถ้าเท่ากับครั้งที่สองวิ่งผ่านไปเลย
                nums[i + 1] = nums[j]
                i += 1
            }
            j += 1
        }
        return i + 1
    }
}

fun main() {
    val s = Solution80()
    val input = intArrayOf(0,0,1,1,1,1,2,3,3)
    println(s.removeDuplicates(input))
    println(input.contentToString())
}