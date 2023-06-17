import kotlin.test.assertEquals

class Solution128 {
    // เก็บ count ความยาวทั้งหมดไว้ที่ขอบสองข้าง
    // ถ้ามีการมาต่อก็คำนวณความยาวใหม่ แล้วก็ไปไว้ที่ขอบสองข้าง
    // ตอน for each in nums ก็เช็คแค่ข้างๆ ตัวเดียวได้เลย คำนวณมาเก็บไว้ที่ขอบแล้ว
    fun longestConsecutive(nums: IntArray): Int {
        val n = nums.distinct()
        val map = mutableMapOf<Int, Int>()
        for (x in n) {
            val lCount = map[x - 1] ?: 0
            val rimL = x - lCount
            val rCount = map[x + 1] ?: 0
            val rimR = x + rCount

            val new = lCount + rCount + 1
            map[rimL] = new
            map[rimR] = new
        }


        return map.maxByOrNull { it.value }?.value ?: 1
    }
}

fun main() {
    val s = Solution128()
    assertEquals(s.longestConsecutive(intArrayOf(100, 4, 200, 1, 3, 2, 5)), 5)
    assertEquals(s.longestConsecutive(intArrayOf(100, 4, 200, 2, 1, 3, 2, 5)), 5)
    assertEquals(s.longestConsecutive(intArrayOf()), 0)
    assertEquals(s.longestConsecutive(intArrayOf(100, 100, 100)), 1)
    assertEquals(s.longestConsecutive(intArrayOf(100, 4)), 1)
    assertEquals(s.longestConsecutive(intArrayOf(-2, 0, 100, 4, 200, 2, 1, 3, 2, 5)), 6)
    assertEquals(s.longestConsecutive(intArrayOf(0, -2, 100, 4, -1, 200, 2, 1, 3, 2, 5)), 8)
}