package advent2021

import java.io.File

private data class Point(val row: Int, val column: Int)

class Day25 {
    private val filename = "/Users/ampos/dotaIsDown/kotlin/src/advent2021/Day25-test.input"
    private var sizeColumn = 0
    private var sizeRow = 0

    fun execute() {
        var l = 0
        var east = mutableSetOf<Point>()
        var south = mutableSetOf<Point>()

        File(filename).forEachLine { line ->
            sizeColumn = line.length
            line.forEachIndexed { index, c ->
                when (c) {
                    '>' -> east.add(Point(l, index))
                    'v' -> south.add(Point(l, index))
                }
            }
            l += 1
        }
        sizeRow = l

        var i = 1
        while (true) {
            val newEast = moveRight(east, south)
            val newSouth = moveDown(newEast, south)

            if (newEast == east && newSouth == south) {
                println("Total step = $i")
                break
            }

            east = newEast
            south = newSouth
            if (i % 100 == 0) {
                printTable(east, south)
            }
            i += 1
        }
    }

    private fun moveRight(east: Set<Point>, south: Set<Point>): MutableSet<Point> {
        val newEast = east.map { point ->
            val targetPos = nextPointRight(point)
            if (targetPos in east || targetPos in south) {
                point
            } else {
                targetPos
            }
        }
        return newEast.toMutableSet()
    }

    private fun nextPointRight(current: Point): Point {
        val newColumn = if (current.column == sizeColumn - 1) {
            0
        } else {
            current.column + 1
        }
        return Point(current.row, newColumn)
    }

    private fun moveDown(east: Set<Point>, south: Set<Point>): MutableSet<Point> {
        val newSouth = south.map { point ->
            val newPos = nextPointDown(point)
            if (newPos in east || newPos in south) {
                point
            } else {
                newPos
            }
        }
        return newSouth.toMutableSet()
    }

    private fun nextPointDown(current: Point): Point {
        val newRow = if (current.row == sizeRow - 1) {
            0
        } else {
            current.row + 1
        }
        return Point(newRow, current.column)
    }

    private fun printTable(east: Set<Point>, south: Set<Point>) {
        (0 until sizeRow).forEach { row ->
            (0 until sizeColumn).forEach { column ->
                when (Point(row, column)) {
                    in east -> {
                        print(">")
                    }
                    in south -> {
                        print("v")
                    }
                    else -> {
                        print(".")
                    }
                }
            }
            println()
        }
    }
}

fun main() {
    val d = Day25()
    d.execute()
}