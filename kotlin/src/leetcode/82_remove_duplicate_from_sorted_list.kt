package leetcode

class Solution82 {
    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }
    fun deleteDuplicates(head: ListNode?): ListNode? {
        var newRoot: ListNode? = null
        var newHead: ListNode? = null
        var h = head
        while (h != null) {
            var dup = false
            while (h?.`val` == h?.next?.`val`) {
                h = h?.next
                dup = true
            }

            if (!dup) {
                if (newHead == null) {
                    newRoot = h
                    newHead = h
                } else {
                    newHead.next = h
                    newHead = h
                }
            }
            h = h?.next
        }
        newHead?.next = null
        return newRoot
    }

    // https://www.youtube.com/watch?v=j7W70djR5ow
    fun deleteDuplicates2(head: ListNode?): ListNode? {
        return recur(head)
    }

    private fun recur(head: ListNode?): ListNode? {
        if (head == null) return null
        if (head.next == null) return head

        var h = head
        var dup = false
        while (h != null && h.`val` == h.next?.`val`) {
            h = h.next
            dup = true
        }

        return if (dup) {
            h = h?.next
            recur(h)
        } else {
            h?.next = recur(h?.next)
            h
        }
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
        val b = ListNode(1)
//        val c = ListNode(2)
//        val d = ListNode(3)
//        val e = ListNode(4)
//        val f = ListNode(4)
//        val g = ListNode(5)
        a.next = b
//        b.next = c
//        c.next = d
//        d.next = e
//        e.next = f
//        f.next = g
        val r = deleteDuplicates2(a)
        print(r)
    }
}

fun main() {
    val s = Solution82()
    s.test()
}