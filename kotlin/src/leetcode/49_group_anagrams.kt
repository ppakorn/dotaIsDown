class Solution49 {
    fun groupAnagrams(strs: Array<String>): List<List<String>> {
        val map = mutableMapOf<String, MutableList<String>>()
        strs.forEach {
            val key = key(it)
            if (map[key] == null) {
                map[key] = mutableListOf(it)
            } else {
                map[key]!!.add(it)
            }
        }

        return map.map { it.value }
    }

    private fun key(s: String): String {
        val a = IntArray(26) { 0 }
        s.forEach {
            a[it - 'a'] += 1
        }
        return a.joinToString()
    }
}

fun main() {
    val s = Solution49()
    println(s.groupAnagrams(arrayOf("eat","tea","tan","ate","nat","bat")))
    println(s.groupAnagrams(arrayOf("")))
    println(s.groupAnagrams(arrayOf("a")))

}