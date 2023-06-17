class Solution26 {
    // i คือ index ของ non-dup จะชี้ตัวล่าสุดของ result
    // j วิ่งนำหน้าไป ถ้าเจอตัวไม่ซ้ำก็เอามาใส่ช่อง i
    fun removeDuplicates(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        var i = 0
        var j = 1
        while (j < nums.size) {
            if (nums[j] > nums[i]) {
                // ถ้ามากกว่าเอามาเติมลงช่อง i ขยับ i
                // ถ้าเท่ากับวิ่งผ่านไปเลย
                nums[i + 1] = nums[j]
                i += 1
            }
            j += 1
        }
        return i + 1
    }
}

fun main() {
    val s = Solution26()
    val nums = intArrayOf(1,2,2,2,3,5,7,7,9)
    println(s.removeDuplicates(nums))
    println(nums.contentToString())
}