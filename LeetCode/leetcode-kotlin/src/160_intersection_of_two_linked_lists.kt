/**
 * Example:
 * var li = ListNode(5)
 * var v = li.`val`
 * Definition for singly-linked list.
 * class ListNode(var `val`: Int) {
 *     var next: ListNode? = null
 * }
 */
private class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

private class Solution160 {
    fun getIntersectionNode(headA:ListNode?, headB:ListNode?):ListNode? {
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
    val c3 = ListNode(33)
    val c2 = ListNode(32).apply { next = c3 }
    val c1 = ListNode(31).apply { next = c2 }

    val b3 = ListNode(23).apply { next = null }
    val b2 = ListNode(22).apply { next = null }
    val b1 = ListNode(21).apply { next = b2 }

    val a2 = ListNode(12).apply { next = null }
    val a1 = ListNode(11).apply { next = a2 }

    val s = Solution160()
    println(s.getIntersectionNode(a1, b1)?.`val`)
}