class Solution21 {
    fun mergeTwoLists(l1: ListNode?, l2: ListNode?): ListNode? {
        if (l1 == null) {
            return l2
        }

        if (l2 == null) {
            return l1
        }

        var i1 = l1
        var i2 = l2
        val result: ListNode
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
    val c3 = ListNode(33)
    val c2 = ListNode(32).apply { next = c3 }
    val c1 = ListNode(31).apply { next = c2 }

    val b3 = ListNode(23).apply { next = null }
    val b2 = ListNode(22).apply { next = b3 }
    val b1 = ListNode(21).apply { next = b2 }

    val a2 = ListNode(12).apply { next = c1 }
    val a1 = ListNode(11).apply { next = a2 }

    val s = Solution21()
    val case1 = s.mergeTwoLists(b3, c3)
    printListNode(case1)
}