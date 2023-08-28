package leetcode

class Solution206 {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }

    fun reverseList(head: ListNode?): ListNode? {
//        return iterative(head)
        return recursive(head)
    }

    private fun iterative(head: ListNode?): ListNode? {
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

    private fun recursive(head: ListNode?): ListNode? {
        // Base case
        if (head?.next == null) {
            return head
        }

        // Reverse the rest
        val newHead = recursive(head.next)

        // Reverse the current next
        head.next?.next = head

        // Cut the line
        head.next = null

        return newHead
    }
}