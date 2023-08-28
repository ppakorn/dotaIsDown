package leetcode

class MinStack() {

    /** initialize your data structure here. */
    private val minHistory = mutableListOf<Int>()
    private val listt = mutableListOf<Int>()

    fun push(x: Int) {
        listt.add(x)
        if (minHistory.isEmpty() || x <= minHistory[minHistory.lastIndex]) {
            minHistory.add(x)
        }
    }

    fun pop() {
        val v = listt.removeAt(listt.lastIndex)
        if (v == minHistory[minHistory.lastIndex]) {
            minHistory.removeAt(minHistory.lastIndex)
        }
    }

    fun top(): Int {
        return listt.last()
    }

    fun getMin(): Int {
        return minHistory[minHistory.lastIndex]
    }

}

fun main() {
    val minStack = MinStack()
    minStack.push(-2)
    minStack.push(0)
    minStack.push(-3)
    println(minStack.getMin()) // return -3

    minStack.pop()
    println(minStack.top()) // return 0

    println(minStack.getMin()) // return -2

}