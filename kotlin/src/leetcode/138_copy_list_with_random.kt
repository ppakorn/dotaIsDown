package leetcode

class Solution138 {
    open class Node(var `val`: Int) {
        var next: Node? = null
        var random: Node? = null
    }

    fun copyRandomList(node: Node?): Node? {
        if (node == null) return null

        val newRoot = Node(node.`val`)
        val oldToNew = mutableMapOf<Node, Node?>(node to newRoot)

        var headOld = node.next
        var headNew: Node? = newRoot

        // Create a linked list with only next, and map old to new
        while (headOld != null) {
            val new = Node(headOld.`val`)
            oldToNew[headOld] = new
            headNew?.next = new
            headNew = new
            headOld = headOld.next
        }

        // Fill the random
        headOld = node
        headNew = newRoot
        while (headOld != null) {
            headNew?.random = oldToNew[headOld.random]
            headNew = headNew?.next
            headOld = headOld.next
        }

        return newRoot
    }

    fun print(node: Node?) {
        var c = node
        while (c != null) {
            print("${c.`val`} ")
            c = c.next
        }
        println()
    }

    fun test() {
        val a = Node(1)
        val b = Node(2)
        val c = Node(3)
        a.next = b
        b.next = c
        print(a)

        val n = copyRandomList(a)
        print(n)
    }
}

fun main() {
    val s = Solution138()
    s.test()
}