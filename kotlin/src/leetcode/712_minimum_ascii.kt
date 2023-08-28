package leetcode

import kotlin.math.max

class Solution712 {
    // Adjust from Longest Common Subsequence
    // If X[i] == Y[j], LCS_table[i][j] = 1 + LCS_table[i-1][j-1]
    // If X[i] != Y[j], LCS_table[i][j] = max(LCS_table[i-1][j], LCS_table[i][j-1])
    fun minimumDeleteSum(s1: String, s2: String): Int {
        val mem = Array(s2.length) { Array(s1.length) { 0 } }
        mem[0][0] = if (s1[0] == s2[0]) {
            s1[0].toInt()
        } else {
            0
        }

        for (i in s2.indices) {
            val c2 = s2[i]
            for (j in s1.indices) {
                val c1 = s1[j]
                if (c1 == c2) {
                    var previous = 0
                    if (i > 0 && j > 0) {
                        previous = mem[i - 1][j - 1]
                    }
                    mem[i][j] = previous + c1.toInt()
                } else if (i > 0 || j > 0) {
                    var up = 0
                    if (i > 0) {
                        up = mem[i - 1][j]
                    }
                    var left = 0
                    if (j > 0) {
                        left = mem[i][j - 1]
                    }
                    mem[i][j] = max(up, left)
                }
            }
        }

        return s1.map { it.toInt() }.sum() + s2.map { it.toInt() }.sum() - mem[s2.lastIndex][s1.lastIndex] * 2
    }
}

fun main() {
    val s = Solution712()
    println(s.minimumDeleteSum("delete", "leet"))
}