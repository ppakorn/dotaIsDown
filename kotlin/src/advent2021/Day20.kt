package advent2021

import java.io.File

class Day20 {
    private val filename = "/Users/ampos/Documents/dota_is_down/kotlin/src/advent2021/Day20.input"
    var algorithm = ""
    var image: MutableList<String> = mutableListOf()
    var darkMode = false

    fun readInput() {
        var lineNumber = 0
        File(filename).forEachLine { line ->
            if (lineNumber == 0) {
                algorithm = line
            }

            if (lineNumber > 1) {
                image.add(line)
            }

            lineNumber += 1
        }
    }

    fun processNewImage() {
        val newImage = mutableListOf<String>()
        for (i in -1 until image.size + 1) {
            var row = ""
            for (j in -1 until image.size + 1) {
                row += process9(i, j)
            }
            newImage.add(row)
        }
        image = newImage
    }

    // Return a new char from algo
    fun process9(i: Int, j: Int): Char {
        val indexBase2 = listOf(
            process1(i - 1, j - 1), process1(i - 1, j), process1(i - 1, j + 1),
            process1(i, j - 1), process1(i, j), process1(i, j + 1),
            process1(i + 1, j - 1), process1(i + 1, j), process1(i + 1, j + 1)
        ).joinToString("")
        val index = indexBase2.toInt(2)
        return algorithm[index]
    }

    // Return 0, 1
    fun process1(i: Int, j: Int): Char {
        return if (isOutOfImage(i, j)) {
            if (darkMode) {
                '1'
            } else {
                '0'
            }
        } else if (image[i][j] == '.') {
            '0'
        } else {
            '1'
        }
    }

    fun isOutOfImage(i: Int, j: Int): Boolean {
        return i < 0 || j < 0 || i >= image.size || j >= image.size
    }

    fun countLight(): Int {
        val count = image.map { row ->
            row.count { it == '#' }
        }.sum()
        return count
    }
}

fun main() {
    val d = Day20()
    d.readInput()

    for (i in 0 until 50) {
        d.processNewImage()
        d.darkMode = !d.darkMode
        println(i)
    }
    println(d.countLight())
}