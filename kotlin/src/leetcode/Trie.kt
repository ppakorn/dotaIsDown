package leetcode

open class TrieNode {
    val children = Array<TrieNode?>(26) { null }
    var isLeaf = false
}

open class Trie {
    val root = TrieNode()

    fun index(char: Char): Int {
        return char - 'a'
    }

    fun insert(word: String) {
        var r = root
        word.forEach {
            val index = index(it)
            if (r.children[index] == null) {
                r.children[index] = TrieNode()
            }
            r = r.children[index]!!
        }
        r.isLeaf = true
    }

    fun search(word: String): Boolean {
        var r = root
        word.forEach {
            val index = index(it)
            r = r.children[index] ?: return false
        }
        return r.isLeaf
    }

    fun beginWith(prefix: String): Boolean {
        var r = root
        prefix.forEach {
            val index = index(it)
            r = r.children[index] ?: return false
        }
        return true
    }
}