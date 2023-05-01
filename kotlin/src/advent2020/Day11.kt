package advent2020

import java.io.File

fun main() {
    val seats = Array(91) { charArrayOf() }
    var l = 0
    File("/Users/ampos/Documents/dota_is_down/LeetCode/leetcode-kotlin/src/advent2020/Day11.input").forEachLine { line ->
        seats[l] = line.toCharArray()
        l += 1
    }

    var hasChanged = true
    while (hasChanged) {
        hasChanged = false
        val oldSeats = copy(seats)
        seats.forEachIndexed { i, row ->
            row.forEachIndexed { j, c ->
                when (c) {
                    'L' -> {
                        val seatsTaken = seeSeatsTaken(oldSeats, i, j)
                        if (seatsTaken == 0) {
                            seats[i][j] = '#'
                            hasChanged = true
                        }
                    }
                    '#' -> {
                        val seatsTaken = seeSeatsTaken(oldSeats, i, j)
                        if (seatsTaken >= 5) {
                            seats[i][j] = 'L'
                            hasChanged = true
                        }
                    }
                }
            }
        }
    }

    var count = 0
    seats.forEach {
        it.forEach {
            if (it == '#') count += 1
        }
    }

    println(count)
}

private fun copy(seats: Array<CharArray>): Array<CharArray> {
    val row = seats.count()
    val column = seats[0].count()

    val new = Array(row) { charArrayOf() }
    (0 until row).forEach { i ->
        new[i] = CharArray(column)
        (0 until column).forEach { j ->
            new[i][j] = seats[i][j]
        }
    }
    return new
}

private fun adjacentSeatsTaken(seats: Array<CharArray>, row: Int, column: Int): Int {
    var takenSeats = 0
    val maxRow = seats.count() - 1
    val maxColumn = seats[0].count() - 1
    if (row > 0 && column > 0 && seats[row - 1][column - 1] == '#')
        takenSeats += 1
    if (row > 0 && seats[row - 1][column] == '#')
        takenSeats += 1
    if (row > 0 && column < maxColumn && seats[row - 1][column + 1] == '#')
        takenSeats += 1
    if (column > 0 && seats[row][column - 1] == '#')
        takenSeats += 1
    if (column < maxColumn && seats[row][column + 1] == '#')
        takenSeats += 1
    if (row < maxRow && column > 0 && seats[row + 1][column - 1] == '#')
        takenSeats += 1
    if (row < maxRow && seats[row + 1][column] == '#')
        takenSeats += 1
    if (row < maxRow && column < maxColumn && seats[row + 1][column + 1] == '#')
        takenSeats += 1
    return takenSeats
}

private fun seeSeatsTaken(seats: Array<CharArray>, row: Int, column: Int): Int {
    var takenSeats = 0
    val maxRow = seats.count()
    val maxColumn = seats[0].count()

    var i = 1
    while (row - i >= 0 && column - i >= 0) {
        if (seats[row - i][column - i] == '#') {
            takenSeats += 1
            break
        }
        if (seats[row - i][column - i] == 'L') {
            break
        }
        i += 1
    }

    i = 1
    while (row - i >= 0) {
        if (seats[row - i][column] == '#') {
            takenSeats += 1
            break
        }
        if (seats[row - i][column] == 'L') {
            break
        }
        i += 1
    }

    i = 1
    while (row - i >= 0 && column + i < maxColumn) {
        if (seats[row - i][column + i] == '#') {
            takenSeats += 1
            break
        }
        if (seats[row - i][column + i] == 'L') {
            break
        }
        i += 1
    }

    i = 1
    while (column - i >= 0) {
        if (seats[row][column - i] == '#') {
            takenSeats += 1
            break
        }
        if (seats[row][column - i] == 'L') {
            break
        }
        i += 1
    }

    i = 1
    while (column + i < maxColumn) {
        if (seats[row][column + i] == '#') {
            takenSeats += 1
            break
        }
        if (seats[row][column + i] == 'L') {
            break
        }
        i += 1
    }

    i = 1
    while (row + i < maxRow && column - i >= 0) {
        if (seats[row + i][column - i] == '#') {
            takenSeats += 1
            break
        }
        if (seats[row + i][column - i] == 'L') {
            break
        }
        i += 1
    }

    i = 1
    while (row + i < maxRow) {
        if (seats[row + i][column] == '#') {
            takenSeats += 1
            break
        }
        if (seats[row + i][column] == 'L') {
            break
        }
        i += 1
    }

    i = 1
    while (row + i < maxRow && column + i < maxColumn) {
        if (seats[row + i][column + i] == '#') {
            takenSeats += 1
            break
        }
        if (seats[row + i][column + i] == 'L') {
            break
        }
        i += 1
    }

    return takenSeats
}
