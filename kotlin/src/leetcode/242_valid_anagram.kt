package leetcode

class Solution242 {
    fun isAnagram(s: String, t: String): Boolean {
        if (s.length != t.length) {
            return false
        }

        val count = mutableMapOf<Char, Int>()
        s.forEach {
            count[it] = (count[it] ?: 0) + 1
        }

        t.forEach {
            count[it] = count[it]?.minus(1) ?: return false
            if (count[it]!! < 0) {
                return false
            }
        }
        return true
    }
}