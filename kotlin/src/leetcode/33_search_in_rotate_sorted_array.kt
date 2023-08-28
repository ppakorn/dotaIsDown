package leetcode

class Solution33 {
    fun search(nums: IntArray, target: Int): Int {
        var i = 0
        var j = nums.size - 1

        while (i <= j) {
            val mid = (i + j) / 2
            val m = nums[mid]
            if (m == target) {
                return mid
            }

            val l = nums[i]
            val r = nums[j]
            if (r >= m) {
                if (target in m + 1..r) {
                    i = mid + 1
                } else {
                    j = mid - 1
                }
            } else {
                if (target in l until m) {
                    j = mid - 1
                } else {
                    i = mid + 1
                }
            }
        }

        return -1
    }
}

fun main() {
    val s = Solution33()
//    println(s.search(intArrayOf(4,5,6,7,0,1,2), 0))
//    println(s.search(intArrayOf(4,5,6,7,0,1,2), 3))
//    println(s.search(intArrayOf(1), 0))
//    println(s.search(intArrayOf(3,1), 0))
//    println(s.search(intArrayOf(1,3), 0))
    println(s.search(intArrayOf(5,1,3), 5))
}