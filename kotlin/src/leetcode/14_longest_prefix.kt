package leetcode

class Solution14 {
    fun longestCommonPrefix(strs: Array<String>): String {
        var prefix = strs[0]
        strs.forEach { s ->
            while (!s.startsWith(prefix)) {
                prefix = prefix.removeRange(prefix.lastIndex, prefix.lastIndex + 1)
            }
            if (prefix.isEmpty()) {
                return prefix
            }
        }
        return prefix
    }
}

fun main() {
    val s = Solution14()
    val result = s.longestCommonPrefix(listOf("flower","flow","flight").toTypedArray())
    println(result)
}