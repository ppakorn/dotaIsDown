package advent2020

import java.io.File
import kotlin.math.abs

class Day12Part2 {
    var shipX = 0
    var shipY = 0
    var waypointX = 10
    var waypointY = 1

    fun execute(){
        File("/Users/ampos/Documents/dota_is_down/LeetCode/leetcode-kotlin/src/advent2020/Day12.input").forEachLine { line ->
            val instruction = line.first()
            val num = line.substring(1).toInt()

            when (instruction) {
                'N' -> { waypointY += num }
                'E' -> { waypointX += num }
                'W' -> { waypointX -= num }
                'S' -> { waypointY -= num }
                'L' -> {
                    when (num) {
                        90 -> waypointL90()
                        180 -> waypoint180()
                        270 -> waypointR90()
                    }
                }
                'R' -> {
                    when (num) {
                        90 -> waypointR90()
                        180 -> waypoint180()
                        270 -> waypointL90()
                    }
                }
                'F' -> {
                    shipX += waypointX * num
                    shipY += waypointY * num
                }
            }
        }

        println(abs(shipX) + abs(shipY))
    }

    private fun waypoint180() {
        waypointX = -waypointX
        waypointY = -waypointY
    }

    private fun waypointL90() {
        val tempX = waypointX
        waypointX = -waypointY
        waypointY = tempX
    }

    private fun waypointR90(){
        val tempX = waypointX
        waypointX = waypointY
        waypointY = -tempX
    }

}

fun main() {
    val d = Day12Part2()
    d.execute()
}
