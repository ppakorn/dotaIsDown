class Solution14 {
    fun hasCycle(head: ListNode?): Boolean {
        if (head == null) return false
        var i1 = head
        var i2 = head.next
        while (i1?.next != null && i2?.next != null){
            if (i1 == i2) {
                return true
            }
            i1 = i1.next
            i2 = i2.next?.next
        }
        return false
    }
}

fun main() {
    val s = Solution14()

}