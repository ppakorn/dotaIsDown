package advent2020

import java.io.File

class Day20_2 {
    private val tiles = mutableMapOf<Int, Tile>()

    fun execute(size: Int = 3) {
        readInput()

        val board = Array(size) { Array<Tile?>(size) { null } }

        val topLeftTile = 2347
//        val topLeftTile = 1951
//        tiles[topLeftTile]!!.flip()

        board[0][0] = tiles[topLeftTile]
        tiles.remove(topLeftTile)
        print("$topLeftTile ")

        (0 until size).forEach row@{ row ->
            if (row > 0) {
                for ((number, tile) in tiles) {
                    if (tryFitting(board[row - 1][0]!!, tile, "TOP")) {
                        board[row][0] = tile
                        tiles.remove(number)
                        print("$number ")
                        break
                    }
                }
            }

            (0 until size - 1).forEach column@{ column ->
                tiles.forEach { (number, tile) ->
                    if (tryFitting(board[row][column]!!, tile, "LEFT")) {
                        board[row][column + 1] = tile
                        tiles.remove(number)
                        print("$number ")
                        return@column
                    }
                }
            }

            println()
        }

//        printBoard(board)

        val image = List(board.size * 8) { "" }.toMutableList()
        board.forEachIndexed { index, row ->
            row.forEach { tile ->
                tile!!.imageWithoutBorder().forEachIndexed { lineIndex, line ->
                    image[index * 8 + lineIndex] += line
                }
            }
        }

//        printImage(image)

        val fsm = FindSeaMonsters(image)
        println(fsm.execute())
    }

    private fun readInput() {
        var i = 0
        var tileStr = mutableListOf<String>()
        var tileNumber = 0
        File("/Users/ampos/Documents/dota_is_down/LeetCode/kotlin/src/advent2020/Day20-Real.input").forEachLine { line ->
//        File("/Users/ampos/Documents/dota_is_down/LeetCode/leetcode-kotlin/src/advent2020/Day20.input").forEachLine { line ->
            // 1 tile use 12 lines
            when (i) {
                0 -> {
                    tileNumber = line.substring(5, 9).toInt()
                    tileStr = mutableListOf()
                }
                in 1..10 -> {
                    tileStr.add(line)
                }
                11 -> {
                    if (line.isNotEmpty()) {
                        throw Exception("This line must be empty")
                    }

                    tiles[tileNumber] = Tile(tileNumber, tileStr)
                }
            }
            i = (i + 1).rem(12)
        }
    }

    private fun tryFitting(previous: Tile, candidate: Tile, direction: String): Boolean {
        candidate.reset()

        for (k in 0 until 4) {
            if (check(previous, candidate, direction)) {
                return true
            }
            candidate.rotateR()
        }

        candidate.reset()
        candidate.flip()

        for (k in 0 until 4) {
            if (check(previous, candidate, direction)) {
                return true
            }
            candidate.rotateR()
        }

        candidate.reset()
        return false
    }

    private fun check(previous: Tile, candidate: Tile, direction: String): Boolean {
        return (direction == "LEFT" && candidate.left == previous.right) ||
                (direction == "TOP" && candidate.up == previous.down)
    }

    private fun printBoard(board: Array<Array<Tile?>>) {
        board.forEach { row ->
            row.forEach { tile ->
                print("${tile?.number} ")
            }
            println()
        }
    }

    private fun printImage(image: List<String>) {
        image.forEach {
            println(it)
        }
    }
}

fun main() {
    val d = Day20_2()
    d.execute(12)
//    Tile.test()
}

