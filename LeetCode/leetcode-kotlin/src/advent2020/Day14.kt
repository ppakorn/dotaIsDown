package advent2020

import java.io.File

class Day14 {
    var maskX = emptySet<Int>()
    var mask1 = emptySet<Int>()
    var mem = mutableMapOf<String, Long>()
    fun main() {
        File("/Users/ampos/Documents/dota_is_down/LeetCode/leetcode-kotlin/src/advent2020/Day14.input").forEachLine { line ->
            val a = line.split(" = ")

            if (a[0] == "mask") {
                maskX = a[1].mapIndexedNotNull { index, c ->
                    if (c == 'X') {
                        index
                    } else {
                        null
                    }
                }.toSet()
                mask1 = a[1].mapIndexedNotNull { index, c ->
                    if (c == '1') {
                        index
                    } else {
                        null
                    }
                }.toSet()
                return@forEachLine
            }

            val value = a[1].toLong()
            val address = a[0].toInt().toString(2).padStart(36, '0').toCharArray()
            maskX.forEach {
                address[it] = 'X'
            }
            mask1.forEach {
                address[it] = '1'
            }
            setValue(address.joinToString(), value)
        }

        println(mem.values.sum())
    }

    private fun setValue(address: String, value: Long) {
        if (!address.contains('X')) {
            mem[address] = value
            return
        }

        val x0 = address.replaceFirst('X', '0')
        setValue(x0, value)
        val x1 = address.replaceFirst('X', '1')
        setValue(x1, value)
    }
}

fun main() {
    val d = Day14()
    d.main()
}