package advent2020

class Day23 {
    fun execute() {
        var current = init("389125467")
        for (i in 0 until 100) {
            current = play(current)
        }
        printAll(current)
//        printFinal(current)
    }

    // Return new current
    private fun play(current: Node): Node {
        var destinationValue = current.value - 1

        val pick = current.next!!
        val pick2 = pick.next!!
        val pick3 = pick2.next!!
        current.next = pick3.next

        val pickSet = setOf(pick.value, pick2.value, pick3.value)
        if (destinationValue == 0) {
            destinationValue = 9
        }
        while (destinationValue in pickSet) {
            destinationValue -= 1
            if (destinationValue == 0) {
                destinationValue = 9
            }
        }

        val destination = find(pick3.next!!, destinationValue)
        pick3.next = destination.next
        destination.next = pick

        return current.next!!
    }

    private fun find(root: Node, value: Int): Node {
        var head = root
        while (head.value != value) {
            head = head.next!!
            if (head.value == root.value) {
                throw Exception("Loop and cannot find $value")
            }
        }
        return head
    }

    private fun init(input: String): Node {
        var root: Node? = null
        var head = root
        input.forEach { i ->
            val node = Node(i - '0', null)
            head?.next = node
            head = node
            if (root == null) {
                root = node
            }
        }

//        for (i in 10 until 1000001) {
//            val node = Node(i, null)
//            head?.next = node
//            head = node
//        }
//        head!!.next = root
        return root!!
    }

    private fun printAll(root: Node, limit: Int = 20) {
        var head = root
        var count = 0
        while (true) {
            print("${head.value} ")
            count += 1
            if (count >= limit) {
                break
            }

            head = head.next!!
            if (head == root) {
                break
            }
        }
        println()
    }

    private fun printFinal(root: Node) {
        var node1 = root
        while (node1.value != 1) {
            node1 = node1.next!!
        }

        node1 = node1.next!!
        while (node1.value != 1) {
            print(node1.value)
            node1 = node1.next!!
        }
    }

    private fun printFinal2(root: Node) {
        var node1 = root
        while (node1.value != 1) {
            node1 = node1.next!!
        }

        println(node1.next!!.value)
        println(node1.next!!.next!!.value)
    }
}

fun main() {
    val d = Day23()
    d.execute()
}
