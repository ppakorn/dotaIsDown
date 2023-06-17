import leetcode.Node
import leetcode.printListNode

class Solution21 {
    fun mergeTwoLists(l1: Node?, l2: Node?): Node? {
        if (l1 == null) {
            return l2
        }

        if (l2 == null) {
            return l1
        }

        var i1 = l1
        var i2 = l2
        val result: Node
        if (i1.`val` < i2.`val`) {
            result = i1
            i1 = i1.next
        } else {
            result = i2
            i2 = i2.next
        }
        var i = result

        while (i1 != null && i2 != null) {
            if (i1.`val` < i2.`val`) {
                i.next = i1
                i1 = i1.next
            } else {
                i.next = i2
                i2 = i2.next
            }
            i = i.next!!
        }

        if (i1 == null) {
            i.next = i2
        } else if (i2 == null) {
            i.next = i1
        }

        return result
    }
}

fun main() {
    val c3 = Node(33)
    val c2 = Node(32).apply { next = c3 }
    val c1 = Node(31).apply { next = c2 }

    val b3 = Node(23).apply { next = null }
    val b2 = Node(22).apply { next = b3 }
    val b1 = Node(21).apply { next = b2 }

    val a2 = Node(12).apply { next = c1 }
    val a1 = Node(11).apply { next = a2 }

    val s = Solution21()
    val case1 = s.mergeTwoLists(b3, c3)
    printListNode(case1)
}