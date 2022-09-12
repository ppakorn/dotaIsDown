package leetcode

class MapSum() {

    /** Initialize your data structure here. */
    val trie = TrieSum()

    fun insert(key: String, `val`: Int) {
        trie.insert(key, `val`)
    }

    fun sum(prefix: String): Int {
        return trie.getSum(prefix)
    }

}

fun main() {
    val ms = MapSum()
    ms.insert("appled", 2)
    ms.insert("apple", 3)
//    ms.insert("apple", 2)
    println(ms.sum("apple"))
}