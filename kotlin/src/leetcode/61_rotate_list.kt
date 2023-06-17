import leetcode.Node

class Solution61 {
    fun rotateRight(head: Node?, k: Int): Node? {
        if (head == null) return null
        var n = 1
        var h: Node = head
        while (h.next != null) {
            n += 1
            h = h.next!!
        }
        val last = h

        if (k % n == 0) return head
        var c = n - (k % n) - 1
        h = head
        while (c > 0) {
            c -= 1
            h = h.next!!
        }

        val newRoot = h.next!!
        h.next = null
        last.next = head
        return newRoot
    }
}