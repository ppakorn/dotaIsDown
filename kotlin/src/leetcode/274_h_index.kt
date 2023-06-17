package leetcode

class Solution274 {
    fun hIndex(citations: IntArray): Int {
        if (citations.size == 1) {
            return if (citations[0] == 0) {
                0
            } else {
                1
            }
        }

        citations.sortDescending()
        var i = 0
        var j = citations.size - 1

        while (i <= j) {
            val mid = (i + j) / 2
            if (citations[mid] == mid + 1) {
                return mid + 1
            } else if (citations[mid] > mid + 1) {
                i = mid + 1
            } else {
                j = mid - 1
            }
        }

        return i
    }
}

fun main() {
    val s = Solution274()
    println(s.hIndex(intArrayOf(2,0,6,1,5)))
    println(s.hIndex(intArrayOf(3,0,6,1,5)))
    println(s.hIndex(intArrayOf(8,0,6,4,5)))
    println(s.hIndex(intArrayOf(1,3,1)))
    println(s.hIndex(intArrayOf(0)))
    println(s.hIndex(intArrayOf(10)))
    println(s.hIndex(intArrayOf(1,1,1,1)))
    println(s.hIndex(intArrayOf(2,2)))
    println(s.hIndex(intArrayOf(2,2,2)))
    println(s.hIndex(intArrayOf(3,3)))
    println(s.hIndex(intArrayOf(3,3,3)))
    println(s.hIndex(intArrayOf(8,10,10,10)))
}