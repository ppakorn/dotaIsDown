package leetcode

class Solution88 {
    // วิ่งจากมากไปน้อย time = O(m + n), space ไม่ต้องประกาศ array ใหม่

    fun merge(nums1: IntArray, m: Int, nums2: IntArray, n: Int): Unit {
        var i = m - 1
        var j = n - 1

        for (k in m + n - 1 downTo 0) {
            if (j < 0 || (i >= 0 && nums1[i] > nums2[j])) {
                nums1[k] = nums1[i]
                i -= 1
            } else {
                nums1[k] = nums2[j]
                j -= 1
            }
        }
    }
}

fun main() {
    val s = Solution88()
    val nums1 = listOf(1,2,3,0,0,0).toIntArray()
    val m = 3
    val nums2 = listOf(2,5,6).toIntArray()
    val n = 3
    s.merge(nums1, m, nums2, n)
    println(nums1.contentToString())
}