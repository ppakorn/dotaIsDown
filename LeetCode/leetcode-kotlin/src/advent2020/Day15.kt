package advent2020

import java.lang.Exception

class Day15 {
    var mem = mutableMapOf<Int, MutableList<Int>>()
    var i = 1
    fun execute(starter: List<Int>): Int {
        var last = -1
        mem = mutableMapOf()
        i = 1
        starter.forEach {
            add(it, i)
            i += 1
            last = it
        }

        while (i <= 30000000) {
            last = calculate(last, i)
            i += 1
        }

        return last
    }

    private fun calculate(last: Int, index: Int): Int {
//        if (mem[last] == null) {
//            mem[last] = mutableListOf(index)
//            return 0
//        }

        val memX = requireNotNull(mem[last])
        return when (memX.count()) {
            1 -> {
                add(0, index)
                0
            }
            2 -> {
                val c = memX[1] - memX[0]
                add(c, index)
                c
            }
            else -> { throw Exception("That's not in my expectation!!!") }
        }
    }

    fun add(x: Int, index: Int) {
        when (mem[x]?.count()) {
            null -> {
                mem[x] = mutableListOf(index)
            }
            1 -> {
                mem[x]!!.add(index)
            }
            2 -> {
                mem[x]!![0] = mem[x]!![1]
                mem[x]!![1] = index
            }
            else -> { throw Exception("WTF") }
        }
    }
}

fun main() {
    val d = Day15()
    println(d.execute(listOf(0, 3, 6)))
//    println(d.execute(listOf(1, 3, 2)))
//    println(d.execute(listOf(2, 1, 3)))
//    println(d.execute(listOf(1, 2, 3)))
//    println(d.execute(listOf(2, 3, 1)))
//    println(d.execute(listOf(3, 2, 1)))
//    println(d.execute(listOf(3, 1, 2)))
    println(d.execute(listOf(7,12,1,0,16,2)))
}
