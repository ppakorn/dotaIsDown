package leetcode

import kotlin.math.min

class Solution1547 {
    fun minCost(n: Int, cuts: IntArray): Int {
        mem.clear()
        val result = dp(0, n, cuts.asList().sorted())
        println(result)
        return result
    }

    private val mem = mutableMapOf<Pair<Int, Int>, Int>()
    private fun dp(i: Int, j: Int, cuts: List<Int>): Int {
        val pair = Pair(i, j)
        if (mem[pair] != null) {
            return mem[pair]!!
        }

        val l = j - i

        if (cuts.isEmpty()) {
            mem[pair] = 0
            return 0
        }

        var min = Int.MAX_VALUE
        cuts.forEachIndexed { index, c ->
            val temp = dp(i, c, cuts.subList(0, index)) + dp(c, j, cuts.subList(index + 1, cuts.size))
            min = min(min, temp)
        }

        mem[pair] = l + min
        return l + min
    }
}

fun main() {
    val s = Solution1547()
    s.minCost(7, listOf(1, 3, 4, 5).toIntArray())
    s.minCost(9, listOf(5,6,1,4,2).toIntArray())
}