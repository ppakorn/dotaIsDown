class SolutionFRI {
    fun findRightInterval(intervals: Array<IntArray>): IntArray {
        val leftToIndex = mutableMapOf<Int, Int>()
        intervals.forEachIndexed { index, interval ->
            if (leftToIndex[interval[0]] == null) {
                leftToIndex[interval[0]] = index
            }
        }

        val result = IntArray(intervals.size) {-1}
        val intervalWithIndexes = intervals
            .mapIndexed { index, interval -> Pair(index, interval) }
            .sortedBy { it.second[1] }
        val keys = leftToIndex.keys.sorted()
        var keyIndex = 0

        intervalWithIndexes.forEach { (oldIndex, interval) ->
            while (keys[keyIndex]!! < interval[1]) {
                keyIndex += 1
                if (keyIndex >= keys.size) {
                    return result
                }
            }
            result[oldIndex] = leftToIndex[keys[keyIndex]]!!
        }

        return result
    }
}

fun main() {
    val s = SolutionFRI()
    println(s.findRightInterval(arrayOf(intArrayOf(1, 2))).contentToString())
    println(s.findRightInterval(arrayOf(intArrayOf(3, 4), intArrayOf(2, 3), intArrayOf(1, 2))).contentToString())
    println(s.findRightInterval(arrayOf(intArrayOf(1, 4), intArrayOf(2, 3), intArrayOf(3, 4))).contentToString())
}