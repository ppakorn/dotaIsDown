package leetcode

import kotlin.math.max

class Solution3 {
    fun lengthOfLongestSubstring(s: String): Int {
        val mem = mutableMapOf<Char, Boolean>()
        var start = 0
        var maxLength = 0

        s.forEachIndexed { end, c ->
            if (mem[c] == true) {
                while (s[start] != c) {
                    mem[s[start]] = false
                    start += 1
                }
                start += 1
            } else {
                maxLength = max(maxLength, end - start + 1)
                mem[c] = true
            }
        }
        maxLength = max(maxLength, s.lastIndex - start + 1)

        return maxLength
    }
}

fun main() {
    val s = Solution3()
    println(s.lengthOfLongestSubstring("abcabcbb"))
    println(s.lengthOfLongestSubstring("bbbbb"))
    println(s.lengthOfLongestSubstring("tmmzuxt"))
}