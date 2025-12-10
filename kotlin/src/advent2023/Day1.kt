package advent2023

import java.io.File
import java.util.regex.Pattern

class Day1 {
//    private val wordToInt = mapOf(
//        "one" to 1,
//        "two" to 2,
//        "three" to 3,
//        "four" to 4,
//        "five" to 5,
//        "six" to 6,
//        "seven" to 7,
//        "eight" to 8,
//        "nine" to 9
//    )

//    Use normal regex won't work in case of twone, it matches two but not one
//    fun execute() {
//        var sum = 0
//        val pattern = Regex("[1-9]|one|two|three|four|five|six|seven|eight|nine")
//
//        File("/Users/pakorn.t/dotaIsDown/kotlin/src/advent2023/Day1.input").forEachLine { line ->
//            val match = pattern.findAll(line)
//            val first = match.firstOrNull()!!.value
//            val last = match.lastOrNull()!!.value
//
//            var temp = 0
//            temp += if (wordToInt[first] != null) {
//                wordToInt[first]!! * 10
//            } else {
//                first.toInt() * 10
//            }
//
//            temp += if (wordToInt[last] != null) {
//                wordToInt[last]!!
//            } else {
//                last.toInt()
//            }
//
//            sum += temp
//        }
//
//        println(sum)
//    }

    // In case the letter is used to form another digit
    private val replace = mapOf(
        "one" to "o1e",
        "two" to "t2o",
        "three" to "t3e",
        "four" to "f4r",
        "five" to "f5e",
        "six" to "s6x",
        "seven" to "s7n",
        "eight" to "e8t",
        "nine" to "n9e"
    )

    fun execute() {
        var sum = 0
        val pattern = Regex("one|two|three|four|five|six|seven|eight|nine")

        File("/Users/pakorn.t/dotaIsDown/kotlin/src/advent2023/Day1.input").forEachLine { line ->
            var a = line
            for ((from, to) in replace) {
                a = a.replace(from, to)
            }

            val first = a.first { it in '1'..'9' } - '0'
            val last = a.last { it in '1'..'9' } - '0'
            sum += first * 10 + last
        }

        println(sum)
    }

}

fun main() {
    val d = Day1()
    d.execute()
}