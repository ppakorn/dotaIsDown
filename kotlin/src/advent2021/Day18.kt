package advent2021

import java.io.File
import kotlin.math.ceil

class Day18 {
    var current = ""
    fun execute() {
        current = ""
        File("/Users/ampos/Documents/dota_is_down/kotlin/src/advent2021/Day18.input").forEachLine { line ->
            if (current.isEmpty()) {
                current = line
                return@forEachLine
            }

            current = add(current, line)
            current = reduce(current)
        }

        println(magnitude(current))
    }

    fun execute2() {
        val numbers = mutableListOf<String>()
        File("/Users/ampos/Documents/dota_is_down/kotlin/src/advent2021/Day18.input").forEachLine { line ->
            numbers.add(line)
        }

        var max = 0
        numbers.forEachIndexed i@{ i, n1 ->
            numbers.forEachIndexed j@{ j, n2 ->
                if (i == j) return@j
                val sum = add(n1, n2)
                val reduced = reduce(sum)
                val magnitude = magnitude(reduced)
                if (magnitude > max) {
                    max = magnitude
                }
            }
        }

        println(max)
    }

    private fun add(a1: String, a2: String) = "[$a1,$a2]"

    private fun reduce(s: String): String {
        var newS = s
        while (true) {
            val reduced1 = reduceDepth(newS)
            val reduced2 = reduceNumber(reduced1)
            if (reduced2 == newS) {
                break
            }
            newS = reduced2
        }
        return newS
    }

    private fun reduceDepth(str: String): String {
        var s = str
        val stack = mutableListOf<Char>()
        var i = 0
        while (i < s.length) {
            when (val c = s[i]) {
                '[' -> {
                    stack += c
                    if (stack.size > 4) {
                        s = explode(s, i)
                        i = -1
                        stack.clear()
                    }
                }
                ']' -> {
                    stack.removeLast()
                }
            }
            i += 1
        }
        return s
    }

    private fun explode(s: String, openIndex: Int): String {
        val prefix = s.substring(0, openIndex)
        val afterPrefix = s.substring(openIndex)
        val closeIndex = afterPrefix.indexOf(']')
        val exploding = afterPrefix.substring(1, closeIndex).split(',').map { it.toInt() }
        val suffix = afterPrefix.substring(closeIndex + 1)

        val newPrefix = addToRightMost(prefix, exploding[0])
        val newSuffix = addToLeftMost(suffix, exploding[1])
        return newPrefix + "0" + newSuffix
    }

    private fun addToRightMost(s: String, n: Int): String {
        var endIndex = s.lastIndex
        while (endIndex >= 0 && s[endIndex] !in '0'..'9') {
            endIndex -= 1
        }

        if (endIndex < 0) {
            return s
        }

        val suffix = s.substring(endIndex + 1)
        var oldNumber = s[endIndex].toString()
        var startIndex = endIndex - 1
        while (s[startIndex] in '0'..'9') {
            oldNumber = s[startIndex] + oldNumber
            startIndex -= 1
        }
        val prefix = s.substring(0, startIndex + 1)
        val newNumber = oldNumber.toInt() + n
        return prefix + newNumber + suffix
    }

    private fun addToLeftMost(s: String, n: Int): String {
        var startIndex = 0
        while (startIndex < s.length && s[startIndex] !in '0'..'9') {
            startIndex += 1
        }

        if (startIndex == s.length) {
            return s
        }

        val prefix = s.substring(0, startIndex)
        var oldNumber = s[startIndex].toString()
        var endIndex = startIndex + 1
        while (s[endIndex] in '0'..'9') {
            oldNumber += s[endIndex]
            endIndex += 1
        }
        val suffix = s.substring(endIndex)
        val newNumber = oldNumber.toInt() + n
        return prefix + newNumber + suffix
    }

    private fun reduceNumber(s: String): String {
        var index = 0
        while (index < s.length) {
            when (val c = s[index]) {
                in '0'..'9' -> {
                    val (n, endIndex) = getNumber(s, index)
                    if (n > 9) {
                        val prefix = s.substring(0, index)
                        val suffix = s.substring(endIndex)
                        return split(prefix, n, suffix)
                    }
                    index = endIndex
                }
                else -> index += 1
            }
        }
        return s
    }

    // Return value and end index
    private fun getNumber(s: String, startIndex: Int): Pair<Int, Int> {
        var index = startIndex
        var n = 0
        while (index < s.length) {
            when (val c = s[index]) {
                in '0'..'9' -> {
                    n = n * 10 + (c - '0')
                    index += 1
                }
                else -> return Pair(n, index)
            }
        }
        return Pair(n, index)
    }

    private fun split(prefix: String, n: Int, suffix: String): String {
        val l = n / 2
        val r = ceil(n / 2.0).toInt()
        val newPair = "[$l,$r]"
        return prefix + newPair + suffix
    }

    private var mgi = 0 // Magnitude Index
    private var mgs = "" // Magnitude string

    fun magnitude(s: String): Int {
        mgi = 0
        mgs = s
        return magnitudeRecur()
    }

    private fun magnitudeRecur(): Int {
        if (mgs[mgi] == '[') {
            mgi += 1
            val l = magnitudeRecur()

            mgi += 1
            if (mgs[mgi] != ',') {
                throw Exception("I thought I would find a , here")
            }

            mgi += 1
            val r = magnitudeRecur()

            mgi += 1
            if (mgs[mgi] != ']') {
                throw Exception("I thought I would find a ] here")
            }

            return 3 * l + 2 * r
        }

        if (mgs[mgi] in '0'..'9') {
            return mgs[mgi] - '0'
        }

        throw Exception("I think too little")
    }
}

fun main() {
    val d = Day18()
    d.execute2()

//    println(d.getNumber("[[[[[9,8],1],2],3],4]", 5))
//    println(d.explode("[7,[6,[5,[4,[3,2]]]]]", 12))
//    println(d.explode("[[6,[5,[4,[3,2]]]],1]", 10))
//    println(d.explode("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]", 10))
//    println(d.explode("[[3,[2,[8,0]]],[9,[5,[4,[3,2]]]]]", 24))

//    println(d.reduceDepth("[[3,[2,[1,[7,3]]]],[6,[5,[4,[3,2]]]]]"))

//    println(d.reduceNumber("[[[[0,7],4],[15,[0,13]]],[1,1]]"))
//    println(d.reduceNumber("[[[[0,7],4],[[7,8],[0,13]]],[1,1]]"))

//    println(d.magnitude("[[1,2],[[3,4],5]]"))
}