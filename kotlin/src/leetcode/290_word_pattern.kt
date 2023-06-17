package leetcode

class Solution290 {
    fun wordPattern(pattern: String, s: String): Boolean {
        val parts = s.split(" ")
        if (pattern.length != parts.size) {
            return false
        }

        val mem = mutableMapOf<Char, String>()
        pattern.forEachIndexed { i, c ->
            if (mem[c] == null) {
                mem[c] = parts[i]
            } else if (mem[c]!! != parts[i]) {
                return false
            }
        }

        return mem.values.toSet().size == mem.size
    }
}