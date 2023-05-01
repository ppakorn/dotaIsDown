package advent2020

import util.toBinary
import kotlin.test.assertEquals

data class Tile(
    val number: Int,
    val original: List<String>
) {
    var up: Int = -1
    var down: Int = -2
    var left: Int = -3
    var right: Int = -4

    var raw = original.toList()
    var action = ""

    init {
        this.reset()
    }

    fun reset() {
        action = ""
        raw = original.toList()
        recalculateIntFromRaw()
    }

    private fun recalculateIntFromRaw() {
        up = toBinary(raw.first())
        down = toBinary(raw.last())
        left = toBinary(raw.map { it.first() }.joinToString(""))
        right = toBinary(raw.map { it.last() }.joinToString(""))
    }

    private fun toBinary(s: String): Int {
        return s.map { c ->
            when (c) {
                '.' -> '0'
                '#' -> '1'
                else -> throw Exception("charToBinary not support $c")
            }
        }.joinToString("").toInt(2)
    }

    fun flip() {
        action += 'F'
        raw = raw.reversed()
        recalculateIntFromRaw()
    }

    fun rotateR() {
        action += 'R'

        val newRaw = mutableListOf<String>()
        raw.forEachIndexed { index, _ ->
            newRaw.add(raw.reversed().map { it[index] }.joinToString(""))
        }
        raw = newRaw
        recalculateIntFromRaw()
    }

    fun imageWithoutBorder(): List<String> {
        return raw
            .subList(1, raw.size - 1)
            .map { it.substring(1, it.length - 1) }
    }

    companion object {
        fun test() {
            val r = listOf(
                "#.#.#####.",
                ".#..######",
                "..#.......",
                "######....",
                "####.#..#.",
                ".#...#.##.",
                "#.#####.##",
                "..#.###...",
                "..#.......",
                "..#.###..."
            )
            val rNoBorder = listOf(
                "#..#####",
                ".#......",
                "#####...",
                "###.#..#",
                "#...#.##",
                ".#####.#",
                ".#.###..",
                ".#......"
            )
        }
    }
}