package leetcode

class Solution228 {
    fun summaryRanges(nums: IntArray): List<String> {
        val results = mutableListOf<String>()
        var ranges = mutableListOf<Int>()

        nums.forEach {
            if (it - (ranges.lastOrNull() ?: Int.MAX_VALUE) == 1) {
                ranges.add(it)
            } else {
                if (ranges.size > 1) {
                    results.add("${ranges.first()}->${ranges.last()}")
                } else if (ranges.isNotEmpty()) {
                    results.add("${ranges.first()}")
                }
                ranges = mutableListOf(it)
            }
        }

        if (ranges.size > 1) {
            results.add("${ranges.first()}->${ranges.last()}")
        } else if (ranges.isNotEmpty()) {
            results.add("${ranges.first()}")
        }

        return results
    }
}

fun main() {
    val s = Solution228()
    println(s.summaryRanges(intArrayOf(0,2,3,4,6,8,9)))
}