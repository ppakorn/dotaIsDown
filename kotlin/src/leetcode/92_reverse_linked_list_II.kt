package leetcode

class Solution92 {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    // แบ่งเป็นสามส่วน เอาตรงกลางไป reverse แล้วมาต่อใหม่
    fun reverseBetween(head: ListNode?, left: Int, right: Int): ListNode? {
        var count = 1
        var h = head

        // Move pointer to before left-th node
        while (h != null && count < left - 1) {
            h = h.next
            count += 1
        }

        val endOfLeftPart = h
        if (count < left) {
            h = h?.next
        }
        val startOfMiddle = h

        count = 0
        while (h != null && count < right - left) {
            h = h.next
            count += 1
        }

        val endOfMiddle = h
        h = h?.next
        val startOfRight = h
        endOfMiddle?.next = null

        val reversedHead = reverse(startOfMiddle)
        startOfMiddle?.next = startOfRight
        if (left == 1) {
            return reversedHead
        } else {
            endOfLeftPart?.next = reversedHead
        }

        return head
    }

    private fun reverse(head: ListNode?): ListNode? {
        var previous: ListNode? = null
        var current = head

        while (current != null) {
            val next = current.next
            current.next = previous

            previous = current
            current = next
        }

        return previous
    }
}

fun main() {
    val a = Solution92.ListNode(1)
    val b = Solution92.ListNode(2)
    val c = Solution92.ListNode(3)
    val d = Solution92.ListNode(4)
    val e = Solution92.ListNode(5)
//    a.next = b
//    b.next = c
//    c.next = d
//    d.next = e

    val s = Solution92()
    s.reverseBetween(a, 1, 1)
}