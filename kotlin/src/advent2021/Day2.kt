package advent2021

import java.io.File

class Day2 {
    fun execute() {
        var hor = 0
        var ver = 0
        var aim = 0
        File("/Users/ampos/Documents/dota_is_down/kotlin/src/advent2021/Day2.input").forEachLine { line ->
            val parts = line.split(" ")
            when (parts[0]) {
                "forward" -> {
                    hor += parts[1].toInt()
                    ver += parts[1].toInt() * aim
                }
                "down" -> aim += parts[1].toInt()
                "up" -> aim -= parts[1].toInt()
            }
        }
        println(hor * ver)
    }
}

fun main() {
    val d = Day2()
    d.execute()
}