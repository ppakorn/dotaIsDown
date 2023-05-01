package leetcode

class Solution1247 {
    fun minimumSwap(s1: String, s2: String): Int {
        var xy = 0
        var yx = 0

        (s1.indices).forEach { i ->
            if (s1[i] == 'x' && s2[i] == 'y') {
                xy += 1
            } else if (s1[i] == 'y' && s2[i] == 'x') {
                yx += 1
            }
        }

        if ((xy + yx) % 2 != 0) return -1
        var result = xy / 2 + yx / 2
        if (xy % 2 == 1 && yx % 2 == 1)
            result += 2
        return result
    }
}

fun main() {
    val s = Solution1247()
    println(s.minimumSwap("xx", "yy"))
}