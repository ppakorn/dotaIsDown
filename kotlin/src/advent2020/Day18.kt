package advent2020

import java.io.File

class Day18 {

    fun execute(string: String): Long {
        val input = string.filter { !it.isWhitespace() }
        val stack = mutableListOf<Pair<Long, Char>>()
        var currentValue: Long? = null
        var currentOperation: Char? = null
        for (c in input) {
            when (c) {
                '+', '*' -> {
                    currentOperation = c
                }
                '(' -> {
                    stack.add(Pair(currentValue ?: 0, currentOperation ?: '+'))
                    currentValue = null
                    currentOperation = null
                }
                ')' -> {
                    if (currentValue == null) {
                        throw Exception("currentValue cannot be null before )")
                    }
                    val store = stack.removeAt(stack.size - 1)
                    currentValue = operate(currentValue, store.second, store.first)
                    currentOperation = null
                }
                else -> {
                    val number = Character.getNumericValue(c).toLong()
                    if (currentOperation == null && currentValue == null)  {
                        currentValue = number
                    } else if (currentOperation != null && currentValue != null) {
                        currentValue = operate(currentValue, currentOperation, number)
                        currentOperation = null
                    } else {
                        throw Exception("Something wrong. currentValue=$currentValue, currentOperation=$currentOperation, number=$number")
                    }
                }
            }
        }

        if (stack.isNotEmpty()) {
            throw Exception("Stack is not empty. $stack")
        }
        return currentValue!!
    }

    private fun operate(val1: Long, operation: Char, val2: Long): Long {
        return when (operation) {
            '+' -> {
                val1 + val2
            }
            '*' -> {
                val1 * val2
            }
            else -> { throw Exception("Not supported operation $operation") }
        }
    }

    fun execute2(string: String): Long {
        val input = string.filter { !it.isWhitespace() }
        val stack = mutableListOf<Pair<Long, Char>>()
        var currentValue: Long? = null
        var currentOperation: Char? = null
        for (c in input) {
            when (c) {
                '+' -> {
                    currentOperation = c
                }
                '*' -> {
                    if (currentValue == null) {
                        throw Exception("currentValue cannot be null before *")
                    }
                    stack.add(Pair(currentValue, '*'))
                    currentValue = null
                    currentOperation = null
                }
                '(' -> {
                    stack.add(Pair(currentValue ?: 0, currentOperation ?: '+'))
                    stack.add(Pair(-1, '('))
                    currentValue = null
                    currentOperation = null
                }
                ')' -> {
                    if (currentValue == null) {
                        throw Exception("currentValue cannot be null before )")
                    }
                    currentOperation = null

                    while (stack.isNotEmpty()) {
                        val store = stack.removeAt(stack.size - 1)
                        if (store.second == '(') {
                            break
                        }
                        currentValue = operate(currentValue!!, store.second, store.first)
                    }

                    while (stack.isNotEmpty() && stack.last().second == '+') {
                        val storePlus = stack.removeAt(stack.size - 1)
                        currentValue = operate(currentValue!!, storePlus.second, storePlus.first)
                    }
                }
                else -> {
                    val number = Character.getNumericValue(c).toLong()
                    if (currentOperation == null && currentValue == null)  {
                        currentValue = number
                    } else if (currentOperation != null && currentValue != null) {
                        currentValue = operate(currentValue, currentOperation, number)
                        currentOperation = null
                    } else {
                        throw Exception("Something wrong. currentValue=$currentValue, currentOperation=$currentOperation, number=$number")
                    }
                }
            }
        }

        stack.forEach {
            if (it.second != '*') {
                throw Exception("Must remain only * in last stage of stack")
            }
            currentValue = currentValue!! * it.first
        }
        return currentValue!!
    }
}

fun main() {
    val d = Day18()
//    val test1 = "1+2*3+4*5+6"
//    val test2 = "1+(2*3)+(4*(5+6))"
//    val test3 = "2 * 3 + (4 * 5)"
//    val test4 = "5 + (8 * 3 + 9 + 3 * 4 * 3)"
//    val test5 = "5 * 9 * (7 * 3 * 3 + 9 * 3 + (8 + 6 * 4))"
//    val test6 = "((2 + 4 * 9) * (6 + 9 * 8 + 6) + 6) + 2 + 4 * 2"
//
//    println(d.execute2(test1))
//    println(d.execute2(test2))
//    println(d.execute2(test3))
//    println(d.execute2(test4))
//    println(d.execute2(test5))
//    println(d.execute2(test6))

    var result = 0L
    File("/Users/ampos/Documents/dota_is_down/LeetCode/leetcode-kotlin/src/advent2020/Day18.input").forEachLine { line ->
        println(line)
        result += d.execute2(line)
    }
    println(result)
}