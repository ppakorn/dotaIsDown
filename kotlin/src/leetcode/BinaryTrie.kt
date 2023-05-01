import util.toBinary

class BinaryTrieNode {
    val children = Array<BinaryTrieNode?>(2) { null }
    var isLeaf = false
}

open class BinaryTrie(
    private val binaryLength: Int
) {
    val root = BinaryTrieNode()

    fun index(char: Char): Int {
        return char - '0'
    }

    fun insert(x: Int) {
        var r = root
        val binary = x.toBinary(binaryLength)
        binary.forEach {
            val index = index(it)
            if (r.children[index] == null) {
                r.children[index] = BinaryTrieNode()
            }
            r = r.children[index]!!
        }
        r.isLeaf = true
    }

    fun search(x: Int): Boolean {
        var r = root
        val binary = x.toBinary(binaryLength)
        binary.forEach {
            val index = index(it)
            r = r.children[index] ?: return false
        }
        return r.isLeaf
    }
}