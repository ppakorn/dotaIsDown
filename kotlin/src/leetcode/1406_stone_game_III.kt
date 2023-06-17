package leetcode

import kotlin.math.max

class Solution1406 {
    fun stoneGameIII(stoneValue: IntArray): String {
        mem = IntArray(stoneValue.size) { Int.MIN_VALUE }
        mem[mem.lastIndex] = stoneValue.last()
        val result = dp(stoneValue, 0)
        return if (result > 0) {
            "Alice"
        } else if (result == 0) {
            "Tie"
        } else {
            "Bob"
        }
    }

    // See explanation from 1140 stone game II, and adapt

    private var mem = IntArray(0)
    private fun dp(stoneValue: IntArray, i: Int): Int {
        if (i > stoneValue.lastIndex) {
            return 0
        }

        if (mem[i] > Int.MIN_VALUE) {
            return mem[i]
        }

        var max = Int.MIN_VALUE
        for (k in 1..3) {
            if (i + k > stoneValue.size) {
                break
            }
            val getValue = stoneValue.slice(i until i + k).sum()
            max = max(max, getValue - dp(stoneValue, i + k))
        }

        mem[i] = max
        return max
    }
}

fun main() {
    val s = Solution1406()
    println(s.stoneGameIII(listOf(1,2,3,-9).toIntArray()))
}