class QTrie: Trie() {
    private var previous = setOf<TrieNode>()
    fun query(letter: Char): Boolean {
        val index = index(letter)

        val temp = previous.mapNotNull {
            it.children[index]
        }.toMutableSet()

        root.children[index]?.let {
            temp.add(it)
        }

        previous = temp
        return temp.any { it.isLeaf }
    }
}

class StreamChecker(words: Array<String>) {
    private val qTrie = QTrie()

    init {
        words.forEach {
            qTrie.insert(it)
        }
    }

    fun query(letter: Char): Boolean {
        return qTrie.query(letter)
    }
}

fun main() {
//    val s = StreamChecker(arrayOf("cd", "f", "jk", "hg"))
//    println("a -> ${s.query('a')}")
//    println("b -> ${s.query('b')}")
//    println("c -> ${s.query('c')}")
//    println("d -> ${s.query('d')}")
//    println("e -> ${s.query('e')}")
//    println("f -> ${s.query('f')}")
//    println("g -> ${s.query('g')}")
//    println("h -> ${s.query('h')}")
//    println("i -> ${s.query('i')}")
//    println("j -> ${s.query('j')}")
//    println("k -> ${s.query('k')}")

    val s2 = StreamChecker(arrayOf("ab","ba","aaab","abab","baa"))
    println(s2.query('a'))
    println(s2.query('a'))
    println(s2.query('a'))
    println(s2.query('a'))
    println(s2.query('a'))
    println(s2.query('b'))
    println(s2.query('a'))
    println(s2.query('b'))
    println(s2.query('a'))
    println(s2.query('b'))
}