package leetcode

import kotlin.math.max

class Solution57 {
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        if (intervals.isEmpty()) return arrayOf(newInterval)

        val result = mutableListOf<IntArray>()
        var i = 0
        while (i < intervals.size && intervals[i][0] < newInterval[0]) {
            result.add(intervals[i])
            i += 1
        }

        val last = result.lastOrNull()
        if (last == null || last[1] < newInterval[0]) {
            // No overlap
            result.add(newInterval)
        } else {
            last[1] = max(newInterval[1], last[1])
        }

        while (i < intervals.size) {
            if (result.last()[1] < intervals[i][0]) {
                // No overlap
                result.add(intervals[i])
            } else {
                result.last()[1] = max(result.last()[1], intervals[i][1])
            }
            i += 1
        }

        return result.toTypedArray()
    }
}

fun main() {
    val s = Solution57()
    val input = transform2DIntArray("[[1,5]]")
    s.insert(input, intArrayOf(1,7)).forEach { println(it.contentToString()) }
}