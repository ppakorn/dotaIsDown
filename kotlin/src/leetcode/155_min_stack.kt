class MinStack() {

    /** initialize your data structure here. */
    var minn = Int.MAX_VALUE
    val listt = mutableListOf<Int>()

    fun push(x: Int) {
        listt.add(x)
        minn = minOf(minn, x)
    }

    fun pop() {
        val recalMin = top() == minn
        listt.removeAt(listt.lastIndex)
        if (listt.isEmpty()) {
            minn = Int.MAX_VALUE
        } else if (recalMin) {
            minn = listt.min()!!
        }
    }

    fun top(): Int {
        return listt.last()
    }

    fun getMin(): Int {
        return minn
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