package leetcode

class Node(var `val`: Int) {
    var next: Node? = null
    var previous: Node? = null
}

fun printListNode(l: Node?) {
    var ll = l
    while (ll != null) {
        print("${ll.`val`} -> ")
        ll = ll.next
    }
}
