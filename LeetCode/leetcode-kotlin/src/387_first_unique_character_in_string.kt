import kotlin.test.assertEquals

class Solution387 {
    fun firstUniqChar(s: String): Int {
        val a = IntArray(26) {-1}
        s.forEachIndexed { index, char ->
            val i = char - 'a'
            when {
                a[i] == -1 -> a[i] = index
                a[i] >= 0 -> a[i] = -2
            }
        }

        var min = Int.MAX_VALUE
        a.forEach { s ->
            if (s >= 0 && s < min) {
                min = s
            }
        }
        return if (min == Int.MAX_VALUE) {
            -1
        } else {
            min
        }
    }
}

fun main() {
    val s = Solution387()
    assertEquals(0, s.firstUniqChar("leetcode"))
    assertEquals(2, s.firstUniqChar("loveleetcode"))
    assertEquals(-1, s.firstUniqChar("leetotdcocdle"))
}