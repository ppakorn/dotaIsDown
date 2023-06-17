import leetcode.Node

private class Solution160 {
    fun getIntersectionNode(headA: Node?, headB: Node?): Node? {
        if (headA == null || headB == null) {
            return null
        }

        var aHead = headA
        var bHead = headB

        var aLength = 0
        while (aHead?.next != null) {
            aLength += 1
            aHead = aHead.next
        }

        var bLength = 0
        while (bHead?.next != null) {
            bLength += 1
            bHead = bHead.next
        }

        aHead = headA
        bHead = headB
        if (aLength > bLength) {
            val dif = aLength - bLength
            aHead = headA
            for (i in 0 until dif) {
                aHead = aHead?.next
            }
        } else if (bLength > aLength) {
            val dif = bLength - aLength
            bHead = headB
            for (i in 0 until dif) {
                bHead = bHead?.next
            }
        }

        while (aHead != null && bHead != null) {
            if (aHead == bHead) {
                return aHead
            }
            aHead = aHead.next
            bHead = bHead.next
        }

        return null
    }
}


fun main() {
    val c3 = Node(33)
    val c2 = Node(32).apply { next = c3 }
    val c1 = Node(31).apply { next = c2 }

    val b3 = Node(23).apply { next = null }
    val b2 = Node(22).apply { next = null }
    val b1 = Node(21).apply { next = b2 }

    val a2 = Node(12).apply { next = null }
    val a1 = Node(11).apply { next = a2 }

    val s = Solution160()
    println(s.getIntersectionNode(a1, b1)?.`val`)
}