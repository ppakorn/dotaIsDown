package leetcode

class Solution150 {
    fun evalRPN(tokens: Array<String>): Int {
        val stack = mutableListOf<Int>()
        tokens.forEach {
            when (it) {
                "+", "-", "*", "/" -> {
                    val a = stack.removeAt(stack.size - 1)
                    val b = stack.removeAt(stack.size - 1)
                    stack.add(operate(a, b, it))
                }
                else -> {
                    stack.add(it.toInt())
                }
            }
        }
        return stack[0]
    }

    private fun operate(a: Int, b: Int, o: String): Int {
        return when (o) {
            "+" -> b + a
            "-" -> b - a
            "*" -> b * a
            "/" -> b / a
            else -> throw Exception("Invalid operation")
        }
    }
}

fun main() {
    val s = Solution150()
    print(s.evalRPN(arrayOf("2","1","+","3","*")))
}