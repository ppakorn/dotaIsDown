package leetcode

class Solution205 {
    fun isIsomorphic(s: String, t: String): Boolean {
        if (s.length != t.length) return false

        val map = mutableMapOf<Char, Char>()
        s.forEachIndexed { i, c ->
            if (map[c] == null) {
                map[c] = t[i]
            } else if (map[c] != t[i]) {
                return false
            }
        }

        // ห้าม map ไปตัวเดียวกัน
        return map.size == map.values.toSet().size
    }
}