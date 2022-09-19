package advent2021

import java.io.File

class Day3 {
    fun execute() {
        val count = IntArray(12) { 0 }
        File("/Users/ampos/Documents/dota_is_down/kotlin/src/advent2021/Day3.input").forEachLine { line ->
            line.forEachIndexed { index, c ->
                when (c) {
                    '0' -> count[index] -= 1
                    '1' -> count[index] += 1
                }
            }
        }

        var gammaStr = ""
        var epsilonStr = ""
        count.forEach { i ->
            if (i > 0) {
                gammaStr += "1"
                epsilonStr += "0"
            } else {
                gammaStr += "0"
                epsilonStr += "1"
            }
        }

        val gamma = gammaStr.toInt(2)
        val epsilon = epsilonStr.toInt(2)
        println(gamma * epsilon)
    }

    fun execute2() {
        val root = Node()
        File("/Users/ampos/Documents/dota_is_down/kotlin/src/advent2021/Day3.input").forEachLine { line ->
            var head = root
            line.forEach { c ->
                val i = c - '0'
                if (head.children[i] == null) {
                    head.children[i] = Node()
                }
                head.count[i] += 1
                head = head.children[i]!!
            }
        }

        var oxygen = ""
        var head: Node? = root
        while (head != null) {
            val most = if (head!!.count[0] > head!!.count[1]) 0 else 1
            head = head!!.children[most]
            if (head != null) {
                oxygen += most.toString()
            }
        }

        var co2 = ""
        head = root
        while (head != null) {
            val count0 = head!!.count[0]
            val count1 = head!!.count[1]
            val least = if (count0 == 0) {
                1
            } else if (count1 == 0) {
                0
            } else if (head!!.count[0] > head!!.count[1]) {
                1
            } else 0
            head = head!!.children[least]
            if (head != null) {
                co2 += least.toString()
            }
        }

        println(oxygen.toInt(2) * co2.toInt(2))

    }

    private class Node {
        var children = Array<Node?>(2) { null }
        var count = IntArray(2) { 0 }
    }
}

fun main() {
    val d = Day3()
    d.execute2()
}