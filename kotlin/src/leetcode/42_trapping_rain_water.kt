package leetcode

class Solution42 {
    // หา highest แล้ววิ่งซ้ายมา highest + ขวามา highest
    fun trap(height: IntArray): Int {
        var i = 0
        var count = 0
        var peak = 0
        var highestIndex = 0
        height.forEachIndexed { index, h ->
            if (h > peak) {
                peak = h
                highestIndex = index
            }
        }

        // Left to right
        peak = 0
        while (i < highestIndex) {
            if (height[i] < peak) {
                // trap water
                count += peak - height[i]
            } else {
                // new peak
                peak = height[i]
            }
            i += 1
        }

        // Right to Left
        i = height.size - 1
        peak = 0
        while (i > highestIndex) {
            if (height[i] < peak) {
                count += peak - height[i]
            } else {
                peak = height[i]
            }
            i -= 1
        }

        return count
    }
}

fun main() {
    val s = Solution42()
    println(s.trap(intArrayOf(0,1,0,2,1,0,1,3,2,1,2,1)))
    println(s.trap(intArrayOf(4,2,0,3,2,5)))
    println(s.trap(intArrayOf(5,4,1,2)))
    println(s.trap(intArrayOf(0)))
}