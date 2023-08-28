package leetcode

import kotlin.math.min

class Solution120 {
    fun minimumTotal(triangle: List<List<Int>>): Int {
        var previous = mutableListOf(0)
        triangle.forEach { row ->
            val current = mutableListOf<Int>()
            row.forEachIndexed { index, i ->
                when (index) {
                    0 -> {
                        current.add(i + previous[0])
                    }
                    row.lastIndex -> {
                        current.add(i + previous[index - 1])
                    }
                    else -> {
                        current.add(i + min(previous[index - 1], previous[index]))
                    }
                }
            }
            previous = current
        }

        return previous.min()!!
    }
}

fun main() {
    val s = Solution120()
    val input = transform2DListInt("[[-2]]")
    println(s.minimumTotal(input))
}