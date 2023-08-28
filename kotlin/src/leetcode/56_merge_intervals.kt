package leetcode

import kotlin.math.max

class Solution56 {
    fun merge(intervals: Array<IntArray>): Array<IntArray> {
        val results = mutableListOf<IntArray>()

        // IMPORTANT: to sort first
        // Same O(nlogn) but a lot easier to sort first than binary search
        intervals.sortBy { it[0] }

        intervals.forEach {
            val lastInterval = results.lastOrNull()
            if (lastInterval == null) {
                results.add(it)
            } else if (it[0] in lastInterval[0]..lastInterval[1]) {
                lastInterval[1] = max(it[1], lastInterval[1])
            } else {
                results.add(it)
            }
        }

        return results.toTypedArray()
    }
}

fun main() {
    val s = Solution56()
    val input = transform2DIntArray("[[1,3],[2,6],[8,10],[15,18]]")
    s.merge(input).forEach { println(it.contentToString()) }
}