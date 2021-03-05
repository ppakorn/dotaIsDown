import util.ithBit

class Solution421 {
    fun findMaximumXOR(nums: IntArray): Int {
        val length = 31
        val trie = BinaryTrie(length)
        nums.forEach {
            trie.insert(it)
        }

        var max = 0
        nums.forEach { x ->
            var root = trie.root
            var newMax = 0
            (length - 1 downTo 0).forEach { i ->
                val ithBit = x.ithBit(i)
                if (ithBit && root.children[0] != null) {
                    root = root.children[0]!!
                    newMax = newMax or (1 shl i)
                } else if (!ithBit && root.children[1] != null) {
                    root = root.children[1]!!
                    newMax = newMax or (1 shl i)
                } else if (root.children[0] != null) {
                    root = root.children[0]!!
                } else {
                    root = root.children[1]!!
                }
            }
            max = maxOf(max, newMax)
        }
        return max

        // First try, keep track of possible bit
//        (0..31).filter { trie.search(it) }.forEach {
//            println("$it")
//        }
//
//        var roots = mutableListOf<Pair<BinaryTrieNode, BinaryTrieNode>>()
//        var resultStr = ""
//        roots.add(Pair(trie.root, trie.root))
//
//        while(!roots[0].first.isLeaf) {
//            val newRoots = mutableListOf<Pair<BinaryTrieNode, BinaryTrieNode>>()
//            var one = false
//            roots.forEach {
//                if (it.first.children[0] != null && it.second.children[1] != null) {
//                    if (!one) {
//                        newRoots.clear()
//                    }
//                    one = true
//                    newRoots.add(Pair(it.first.children[0]!!, it.second.children[1]!!))
//                }
//                if (it.first != it.second && it.first.children[1] != null && it.second.children[0] != null) {
//                    if (!one) {
//                        newRoots.clear()
//                    }
//                    one = true
//                    newRoots.add(Pair(it.first.children[1]!!, it.second.children[0]!!))
//                }
//                if (one)
//                    return@forEach
//
//                if (it.first.children[0] != null && it.second.children[0] != null) {
//                    newRoots.add(Pair(it.first.children[0]!!, it.second.children[0]!!))
//                }
//                if (it.first.children[1] != null && it.second.children[1] != null) {
//                    newRoots.add(Pair(it.first.children[1]!!, it.second.children[1]!!))
//                }
//            }
//            resultStr += if (one) {
//                '1'
//            } else {
//                '0'
//            }
//            roots = newRoots
//        }
//
//        while (resultStr.length < length) {
//            resultStr += '0'
//        }
//
//        return resultStr.toInt(2)
    }
}

fun main() {
    val s = Solution421()
    println(s.findMaximumXOR(intArrayOf(3, 10, 5, 25, 2, 8)))
    println(s.findMaximumXOR(intArrayOf(13, 15, 11, 8)))
}