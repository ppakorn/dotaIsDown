class ListNode(var `val`: Int) {
    var next: ListNode? = null
}

fun printListNode(l: ListNode?) {
    var ll = l
    while (ll != null) {
        print("${ll.`val`} -> ")
        ll = ll.next
    }
}