package leetcode

import java.util.*

class MyStack225() {
    // Follow question: Implement using only 1 queue

    // Current implementation:
    // O(1) for push and empty
    // O(n) for top and pop

    // If top is called often, can be change to
    // O(1) for pop, top, empty
    // O(n) for push
    // By adding to queue the rotate until the newest is ready to deque
    private val q: Queue<Int> = LinkedList()

    fun push(x: Int) {
        q.add(x)
    }

    fun pop(): Int {
        var i = 0
        var x = 0
        val beforeSize = q.size
        while (i < beforeSize) {
            x = q.remove()
            if (i < beforeSize - 1) {
                q.add(x)
            }
            i += 1
        }
        return x
    }

    fun top(): Int {
        var i = 0
        var x = 0
        while (i < q.size) {
            x = q.remove()
            q.add(x)
            i += 1
        }
        return x
    }

    fun empty(): Boolean {
        return q.isEmpty()
    }

}

fun main() {
    val s = MyStack225()
    s.push(1)
    s.push(2)
    println(s.top())
    s.push(3)
    println(s.pop())
    println(s.pop())
    println(s.empty())
    println(s.pop())
    println(s.empty())}