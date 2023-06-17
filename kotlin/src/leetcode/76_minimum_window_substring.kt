package leetcode

class Solution76 {
    fun minWindow(s: String, t: String): String {
        val countT = mutableMapOf<Char, Int>()
        t.forEach {
            countT[it] = (countT[it] ?: 0) + 1
        }

        var start = 0
        var result: String? = null
        val countS = mutableMapOf<Char, Int>()

        s.forEachIndexed { end, c ->
            if (countT.keys.contains(c)) {
                countS[c] = (countS[c] ?: 0) + 1
            }

            while (equalOrGreaterThanMap(countT, countS)) {
                val candidate = s.substring(start..end)
                if (candidate.length < (result?.length ?: Int.MAX_VALUE)) {
                    result = candidate
                }
                countS[s[start]] = (countS[s[start]] ?: 0) - 1
                if (countS[s[start]]!! <= 0) {
                    countS.remove(s[start])
                }
                start += 1
            }
        }

        return result ?: ""
    }

    private fun equalOrGreaterThanMap(threshold: Map<Char, Int>, test: Map<Char, Int>): Boolean {
        if (threshold.size != test.size) {
            return false
        }
        if (threshold.keys != test.keys) {
            return false
        }
        threshold.entries.forEach {
            if ((test[it.key] ?: 0) < it.value) {
                return false
            }
        }
        return true
    }
}

fun main() {
    val s = Solution76()
    println(s.minWindow("ADOBECODEBANCAB", "ABC"))
}