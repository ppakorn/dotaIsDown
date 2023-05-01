class Solution142 {

    // let a = distance from start node to start of cycle
    // let b = distance from start of cycle to the node two pointers met
    // let r = total distance in cycle
    // let c = r - b
    // Thus, til the meeting node the slower pointer travelled = a + b
    // the faster travelled = a + b + c + b
    // and because of the faster pointer travels with double speed
    // We got 2(a + b) = a + 2b + c
    // Thus a = c

    fun detectCycle(head: ListNode?): ListNode? {
        if (head == null) return null
        var i1 = head
        var i2 = head
        while (i1 != null && i2 != null) {
            i1 = i1.next
            i2 = i2.next?.next

            if (i1 == i2) {
                break
            }
        }

        if (i1 == null || i2 == null)
            return null

        i1 = head
        while (true) {
            if (i1 == i2) {
                return i1
            }

            i1 = i1?.next
            i2 = i2?.next
        }
    }
}

fun main() {
    val a1 = ListNode(1)
    val a2 = ListNode(2)
    val a3 = ListNode(3)
    val a4 = ListNode(4)
    a1.next = a2
    a2.next = a3
    a3.next = a4
    a4.next = a2

    val s = Solution142()
    println(s.detectCycle(a1)?.`val`)
}