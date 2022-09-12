package advent2021

import java.io.File

class Day13 {
    fun execute() {
        val initialPoints = mutableSetOf<Pair<Int, Int>>()
        var points = emptySet<Pair<Int, Int>>()
        File("/Users/ampos/Documents/dota_is_down/kotlin/src/advent2021/Day13.input").forEachLine { line ->
            if (line.isBlank()) {
                points = initialPoints
            } else if (line.startsWith("fold")) {
                val a = line.substringAfter("fold along ").split("=")
                points = if (a[0] == "x") {
                    foldHorizontal(points, a[1].toInt())
                } else {
                    foldVertical(points, a[1].toInt())
                }
            } else {
                val a = line.split(",").map { it.toInt() }
                initialPoints.add(Pair(a[0], a[1]))
            }
        }

        printPoints(points)
    }

    private fun foldHorizontal(points: Set<Pair<Int, Int>>, center: Int): Set<Pair<Int, Int>> {
        return points.map { old ->
            if (old.first < center) {
                old
            } else {
                val distance = old.first - center
                Pair(center - distance, old.second)
            }
        }.toSet()
    }

    private fun foldVertical(points: Set<Pair<Int, Int>>, center: Int): Set<Pair<Int, Int>> {
        return points.map { old ->
            if (old.second < center) {
                old
            } else {
                val distance = old.second - center
                Pair(old.first, center - distance)
            }
        }.toSet()
    }

    private fun printPoints(points: Set<Pair<Int, Int>>) {
        val sizeX = 40
        val sizeY = 6
        (0 until sizeY).forEach { y ->
            (0 until sizeX).forEach { x ->
                if (points.contains(Pair(x, y))) {
                    print("#")
                } else {
                    print(".")
                }
            }
            println()
        }
    }
}

fun main() {
    val d = Day13()
    d.execute()
}