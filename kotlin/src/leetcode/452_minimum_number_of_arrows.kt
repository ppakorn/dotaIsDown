package leetcode

import kotlin.math.max
import kotlin.math.min

class Solution452 {
    fun findMinArrowShots(points: Array<IntArray>): Int {
        points.sortBy { it[0] }
        var overlap: IntArray? = null
        var count = 0

        points.forEach {
            if (overlap != null && it[0] in overlap!![0]..overlap!![1]) {
                overlap!![0] = max(overlap!![0], it[0])
                overlap!![1] = min(overlap!![1], it[1])
            } else {
                overlap = it
                count += 1
            }
        }

        return count
    }
}

fun main() {
    val s = Solution452()
    val input = transform2DIntArray("[[1,2],[2,3],[2,4],[3,5]]")
    println(s.findMinArrowShots(input))
}