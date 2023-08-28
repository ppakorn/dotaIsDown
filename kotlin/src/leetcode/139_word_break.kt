package leetcode

class Solution139 {
    private val visited = mutableSetOf<String>()
    fun wordBreak(s: String, wordDict: List<String>): Boolean {
        visited.clear()
        val map = mutableMapOf<Char, MutableSet<String>>()
        wordDict.forEach {
            if (map[it[0]] == null) {
                map[it[0]] = mutableSetOf()
            }
            map[it[0]]!!.add(it)
        }
        return recur(s, map)
    }

    private fun recur(s: String, wordDict: Map<Char, Set<String>>): Boolean {
        if (visited.contains(s)) {
            return false
        }

        if (wordDict[s[0]]?.contains(s) == true) {
            return true
        }

        wordDict[s[0]]?.forEach { prefix ->
            if (prefix.length > s.length)
                return@forEach
            val l = s.substring(0, prefix.length)
            val r = s.substring(prefix.length)
            if (l == prefix && recur(r, wordDict)) {
                return true
            }
        }

        visited.add(s)
        return false
    }
}

fun main() {
    val s = Solution139()
    println(s.wordBreak("leetcode", listOf("lee","ode","t","tc")))
    println(s.wordBreak("applepenapple", listOf("apple","pen")))
    println(s.wordBreak("catsandog", listOf("cats","dog","sand","and","cat")))
    println(s.wordBreak("catsandog", listOf("a","c","d","o","g","t","s","n")))
    println(s.wordBreak("aaaaaaaa", listOf("aaa", "aaaaa")))
    println(s.wordBreak("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab", listOf("a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa")))
}
