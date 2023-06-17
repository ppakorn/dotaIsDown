package leetcode

class Solution27 {
    // swap val ไปข้างหลังเรื่อยๆ
    fun removeElement(nums: IntArray, `val`: Int): Int {
        var i = -1
        var j = nums.size

        while (i < j) {
            // Move i until found val
            i += 1
            while (i < j && nums[i] != `val`) {
                i += 1
            }

            // Move j until found not val
            j -= 1
            while (i < j && nums[j] == `val`) {
                j -= 1
            }

            // Swap value i and j
            if (i < j) {
                nums[i] = nums[j]
                nums[j] = `val`
            }
        }

        return i
    }
}

fun main() {
    val s = Solution27()
    val arr = listOf(0,1,2,2,3,0,4,2).toIntArray()
    println(s.removeElement(arr, 2))
    println(arr.contentToString())
}