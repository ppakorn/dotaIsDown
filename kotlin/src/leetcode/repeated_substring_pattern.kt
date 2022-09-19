class SolutionRSP {
    fun repeatedSubstringPattern(s: String): Boolean {
        val s0 = s[0]
        val s0Indexes = s
            .mapIndexed { index, c ->
                if (c == s0) {
                    index
                } else {
                    null
                }
            }
            .filterNotNull()
        val size = s0Indexes.size
        (1..size/2).forEach { distance ->
            if (size % distance != 0) return@forEach

            val length = s0Indexes[distance]
            val str = s.slice(0 until s0Indexes[distance])
            var i = distance
            while (i < size) {
                val nextI = if (i + distance > size) {
                    return@forEach
                } else if (i + distance == size) {
                    s.length
                } else {
                    s0Indexes[i + distance]
                }
                val newLength = nextI - s0Indexes[i]
                if (newLength != length)
                    return@forEach

                val newStr = s.slice(s0Indexes[i] until nextI)
                if (newStr != str)
                    return@forEach

                i += distance
            }

            if (i >= size) return true
        }
        return false
    }
}

fun main() {
    val s = SolutionRSP()
    println(s.repeatedSubstringPattern("aba"))
    println(s.repeatedSubstringPattern("abcabcabc"))
    println(s.repeatedSubstringPattern("ababcababc"))
}