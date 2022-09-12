package advent2020

class Day23_2 {
    private val after = mutableMapOf<Int, Int>()
    private val max = 1000000

    fun execute() {
        val starter = "643719258"
//        val starter = "389125467"
        init(starter)
        var head = starter[0] - '0'
//        printAll(head)
//        println(after[1000000])
        for (i in 0 until 10000000) {
            play(head)
            head = after[head]!!
        }
        println(after[1])
        println(after[after[1]])
    }

    private fun play(current: Int) {
        val pick1 = after[current]!!
        val pick2 = after[pick1]!!
        val pick3 = after[pick2]!!
        val pickSet = setOf(pick1, pick2, pick3)

        var destination = current - 1
        if (destination == 0) {
            destination = max
        }
        while (destination in pickSet) {
            destination -= 1
            if (destination == 0) {
                destination = max
            }
        }

        after[current] = after[pick3]!!
        after[pick3] = after[destination]!!
        after[destination] = pick1
    }

    private fun init(starter: String) {
        var previous = -1
        starter.forEach { i ->
            val v = i - '0'
            if (previous == -1) {
                previous = v
                return@forEach
            }
            after[previous] = v
            previous = v
        }

        after[previous] = 10
        for (i in 10 until 1000000) {
            after[i] = i + 1
        }

        val last = 1000000
        val first = starter.first() - '0'
        after[last] = first
    }

    private fun printAll(root: Int) {
        print("$root ")
        var count = 1
        var head = after[root]
        while (head != root) {
            print("$head ")
            count += 1
            if (count >= 20) {
                break
            }
            head = after[head]
        }
        println()
    }
}

fun main() {
    val d = Day23_2()
    d.execute()
}