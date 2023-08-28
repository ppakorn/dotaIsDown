package leetcode

class Solution19 {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }
    fun removeNthFromEnd(head: ListNode?, n: Int): ListNode? {
        var size = 0
        var h = head
        while (h != null) {
            size += 1
            h = h.next
        }

        val nFromStart = size - n   // start with index 0
        if (nFromStart == 0) {
            return head?.next
        }

        var i = 0
        h = head

        // run to the node before to-be-removed node
        while (i < nFromStart - 1) {
            h = h?.next
            i++
        }

        // Remove h.next
        h?.next = h?.next?.next
        return head
    }

    fun print(head: ListNode?) {
        var h = head
        while (h != null) {
            print("${h.`val`} ")
            h = h.next
        }
        println()
    }

    fun test() {
        val a = ListNode(1)
        val b = ListNode(2)
        val c = ListNode(3)
        val d = ListNode(4)
        val e = ListNode(5)
//        val f = ListNode(4)
//        val g = ListNode(5)
        a.next = b
//        b.next = c
//        c.next = d
//        d.next = e
//        e.next = f
//        f.next = g
        val r = removeNthFromEnd(a, 2)
        print(r)
    }
}

fun main() {
    val s = Solution19()
    s.test()
}