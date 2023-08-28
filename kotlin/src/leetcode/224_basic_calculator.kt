package leetcode

class Solution224 {
    fun calculate(s: String): Int {
        val str = removeSpace(s)

        var i = 0
        var mem = 0
        var operation = '+'
        val stack = mutableListOf<Pair<Int, Char>>()

        while (i < str.length) {
            when (str[i]) {
                '+', '-' -> {
                    operation = str[i]
                }
                '(' -> {
                    stack.add(Pair(mem, operation))
                    mem = 0
                    operation = '+'
                }
                ')' -> {
                    val previous = stack.removeAt(stack.size - 1)
                    mem = operate(previous.first, mem, previous.second)
                }
                else -> {
                    var number = ""
                    while (i < str.length && str[i].isDigit()) {
                        number += str[i]
                        i += 1
                    }

                    // เดี๋ยวโดน + ข้างนอกอีกที
                    i -= 1

                    val n = number.toInt()
                    mem = operate(mem, n, operation)
                }
            }

            i += 1
        }

        return mem
    }

    private fun removeSpace(s: String): String {
        return s.replace(" ", "")
    }

    private fun operate(a: Int, b: Int, o: Char): Int {
        return when (o) {
            '+' -> a + b
            '-' -> a - b
            else -> throw Exception("Unsupported operation")
        }
    }
}

fun main() {
    val s = Solution224()
    println(s.calculate("(1+(4+5+2)-3)+(6+8)"))
}
