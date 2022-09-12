package leetcode

class TrieNodeWithValue {
    val children = Array<TrieNodeWithValue?>(26) { null }
    var isLeaf = false
    var value: Int = 0
    var sum: Int = 0
}

class TrieSum {
    val root = TrieNodeWithValue()

    fun insert(word: String, v: Int) {
        var r = root
        val oldValue = getValue(word)
        word.forEach {
            val index = index(it)
            if (r.children[index] == null) {
                r.children[index] = TrieNodeWithValue()
            }
            r = r.children[index]!!
            r.sum -= oldValue
            r.sum += v
        }
        r.isLeaf = true
        r.value = v
    }

    private fun index(char: Char): Int {
        return char - 'a'
    }

    private fun getValue(word: String): Int {
        var r = root
        word.forEach {
            val index = index(it)
            r = r.children[index] ?: return 0
        }
        return r.value
    }

    fun getSum(prefix: String): Int {
        var r = root
        prefix.forEach {
            val index = index(it)
            r = r.children[index] ?: return 0
        }
        return r.sum
    }
}