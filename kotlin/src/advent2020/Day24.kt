package advent2020

import java.io.File

class Day24 {
    fun execute(): Int {
        val starter = mutableSetOf<Pair<Int, Int>>()
        File("/Users/ampos/Documents/dota_is_down/kotlin/src/advent2020/Day24.input").forEachLine { line ->
            val key = key(line)
            if (starter.contains(key)) {
                starter.remove(key)
            } else {
                starter.add(key)
            }
        }

        var blacks = starter.toSet()
        for (i in 0 until 100) {
            blacks = dayPass(blacks)
        }
        return blacks.count()
    }

    private fun dayPass(blacks: Set<Pair<Int, Int>>): Set<Pair<Int, Int>> {
        val newBlacks = mutableSetOf<Pair<Int, Int>>()
        blacks.forEach { black ->
            // apply black rule to self
            val blackAroundCount = countBlack(black.first, black.second, blacks)
            if (blackAroundCount == 1 || blackAroundCount == 2) {
                newBlacks.add(black)
            }

            // apply white rule to whites around it
            val whitesAround = whitesAround(black.first, black.second, blacks)
            val noFlipYet = whitesAround - newBlacks
            noFlipYet.forEach { white ->
                val blackCount = countBlack(white.first, white.second, blacks)
                if (blackCount == 2) {
                    newBlacks.add(white)
                }
            }
        }
        return newBlacks
    }

    private fun whitesAround(x: Int, y: Int, blacks: Set<Pair<Int, Int>>): Set<Pair<Int, Int>> {
        val whites = mutableSetOf<Pair<Int, Int>>()
        val e = Pair(x + 1, y)
        val w = Pair(x - 1, y)
        val ne: Pair<Int, Int>
        val nw: Pair<Int, Int>
        val se: Pair<Int, Int>
        val sw: Pair<Int, Int>

        if (y % 2 == 0) {
            ne = Pair(x + 1, y + 1)
            se = Pair(x + 1, y - 1)
            nw = Pair(x, y + 1)
            sw = Pair(x, y - 1)
        } else {
            ne = Pair(x, y + 1)
            se = Pair(x, y - 1)
            nw = Pair(x - 1, y + 1)
            sw = Pair(x - 1, y - 1)
        }

        if (e !in blacks) whites.add(e)
        if (w !in blacks) whites.add(w)
        if (ne !in blacks) whites.add(ne)
        if (nw !in blacks) whites.add(nw)
        if (se !in blacks) whites.add(se)
        if (sw !in blacks) whites.add(sw)

        return whites
    }

    private fun countBlack(x: Int, y: Int, blacks: Set<Pair<Int, Int>>): Int {
        var count = 0
        val e = Pair(x + 1, y)
        val w = Pair(x - 1, y)
        val ne: Pair<Int, Int>
        val nw: Pair<Int, Int>
        val se: Pair<Int, Int>
        val sw: Pair<Int, Int>

        if (y % 2 == 0) {
            ne = Pair(x + 1, y + 1)
            se = Pair(x + 1, y - 1)
            nw = Pair(x, y + 1)
            sw = Pair(x, y - 1)
        } else {
            ne = Pair(x, y + 1)
            se = Pair(x, y - 1)
            nw = Pair(x - 1, y + 1)
            sw = Pair(x - 1, y - 1)
        }

        if (e in blacks) count += 1
        if (w in blacks) count += 1
        if (ne in blacks) count += 1
        if (nw in blacks) count += 1
        if (se in blacks) count += 1
        if (sw in blacks) count += 1

        return count
    }

    private fun key(path: String): Pair<Int, Int> {
        var carry: Char? = null
        var x = 0
        var y = 0
        path.forEach { char ->
            when (char) {
                'n', 's' -> {
                    if (carry != null) {
                        throw Exception("has carry $carry, but get $char")
                    }
                    carry = char
                }
                'e' -> {
                    when (carry) {
                        'n' -> {
                            if (y % 2 == 0) {
                                x += 1
                            }
                            y += 1
                        }
                        's' -> {
                            if (y % 2 == 0) {
                                x += 1
                            }
                            y -= 1
                        }
                        null -> x += 1
                    }
                    carry = null
                }
                'w' -> {
                    when (carry) {
                        'n' -> {
                            if (y % 2 == 1 || y % 2 == -1) {
                                x -= 1
                            }
                            y += 1
                        }
                        's' -> {
                            if (y % 2 == 1 || y % 2 == -1) {
                                x -= 1
                            }
                            y -= 1
                        }
                        null -> x -= 1
                    }
                    carry = null
                }
            }
        }
        return Pair(x, y)
    }
}

fun main() {
    val d = Day24()
    print(d.execute())
}