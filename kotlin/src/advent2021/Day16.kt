package advent2021

import java.io.File
import java.lang.Exception

class Day16 {
    private var totalVersion = 0
    fun execute(s: String): Long {
//        totalVersion = 0
//        decode(s, 0)
//        return totalVersion

        return decode2(s, 0).second
    }

    // Return end index of this packet exclusively
//    private fun decode(s: String, startIndex: Int): Int {
//        var pointer = startIndex
//        val version = s.substring(startIndex, startIndex + 3).toInt(2)
//        totalVersion += version
//        pointer += 3
//
//        val operation = s.substring(pointer, pointer + 3).toInt(2)
//        pointer += 3
//
//        if (operation == 4) {
//            var i = pointer
//            while (s[i] == '1') {
//                i += 5
//            }
//            return i + 5
//        } else {
//            val mode = s.substring(startIndex + 6, startIndex + 7).toInt(2)
//            pointer += 1
//
//            if (mode == 0) {
//                val length = s.substring(pointer, pointer + 15).toInt(2)
//                pointer += 15
//                val limit = pointer + length
//                while (pointer < limit) {
//                    pointer = decode(s, pointer)
//                }
//                return pointer
//            } else {
//                val subpackets = s.substring(pointer, pointer + 11).toInt(2)
//                pointer += 11
//                repeat(subpackets) {
//                    pointer = decode(s, pointer)
//                }
//                return pointer
//            }
//        }
//    }

    // Return end index of this packet exclusively, and value
    private fun decode2(s: String, startIndex: Int): Pair<Int, Long> {
        var pointer = startIndex
        val version = s.substring(startIndex, startIndex + 3).toInt(2)
        totalVersion += version
        pointer += 3

        val operation = s.substring(pointer, pointer + 3).toInt(2)
        pointer += 3

        if (operation == 4) {
            var i = pointer
            var value = ""
            while (s[i] == '1') {
                value += s.substring(i + 1, i + 5)
                i += 5
            }
            value += s.substring(i + 1, i + 5)
            return Pair(i + 5, value.toLong(2))
        } else {
            val mode = s.substring(startIndex + 6, startIndex + 7).toInt(2)
            pointer += 1

            val values = mutableListOf<Long>()
            if (mode == 0) {
                val length = s.substring(pointer, pointer + 15).toInt(2)
                pointer += 15
                val limit = pointer + length
                while (pointer < limit) {
                    val sub = decode2(s, pointer)
                    pointer = sub.first
                    values.add(sub.second)
                }
            } else {
                val subpacketTimes = s.substring(pointer, pointer + 11).toInt(2)
                pointer += 11
                repeat(subpacketTimes) {
                    val sub = decode2(s, pointer)
                    pointer = sub.first
                    values.add(sub.second)
                }
            }
            return Pair(pointer, calculateValue(operation, values))
        }
    }

    private fun calculateValue(operation: Int, values: List<Long>): Long {
        return when (operation) {
            0 -> values.sum()
            1 -> values.reduce { acc, x -> acc * x}
            2 -> values.minOrNull()!!
            3 -> values.maxOrNull()!!
            5 -> if (values[0] > values[1]) 1 else 0
            6 -> if (values[0] < values[1]) 1 else 0
            7 -> if (values[0] == values[1]) 1 else 0
            else -> throw Exception("Does not recognize operation $operation")
        }
    }
}

fun main() {
    val d = Day16()
    val hexToBinary = mapOf(
        '0' to "0000",
        '1' to "0001",
        '2' to "0010",
        '3' to "0011",
        '4' to "0100",
        '5' to "0101",
        '6' to "0110",
        '7' to "0111",
        '8' to "1000",
        '9' to "1001",
        'A' to "1010",
        'B' to "1011",
        'C' to "1100",
        'D' to "1101",
        'E' to "1110",
        'F' to "1111"
    )

    File("/Users/ampos/Documents/dota_is_down/kotlin/src/advent2021/Day16.input").forEachLine { line ->
        val s = line.map { hexToBinary[it] }.joinToString("")
        println(d.execute(s))
    }

}