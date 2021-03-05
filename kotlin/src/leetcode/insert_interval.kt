class SolutionINI {
    fun insert(intervals: Array<IntArray>, newInterval: IntArray): Array<IntArray> {
        val first = intervals.indexOfFirst {
            it[1] >= newInterval[0]
        }
        val last = intervals.indexOfLast {
            it[0] <= newInterval[1]
        }
        if (first == -1) return intervals + arrayOf(newInterval)
        if (last == -1) return arrayOf(newInterval) + intervals

        val new = intArrayOf(
            minOf(intervals[first][0], newInterval[0]),
            maxOf(intervals[last][1], newInterval[1])
        )

        val result = mutableListOf<IntArray>()
        (0 until first).forEach {
            result.add(intervals[it])
        }
        result.add(new)
        (last+1 until intervals.size).forEach {
            result.add(intervals[it])
        }

        return result.toTypedArray()
    }
}

fun main() {
    val s = SolutionINI()
    println(s.insert(
        arrayOf(),
        intArrayOf(2,5)
    ))
}