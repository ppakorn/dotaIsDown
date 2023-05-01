package advent2020

import java.io.File
import kotlin.math.abs

fun main() {
    var face = 0
    var posX = 0
    var posY = 0
    File("/Users/ampos/Documents/dota_is_down/LeetCode/leetcode-kotlin/src/advent2020/Day12.input").forEachLine { line ->
        val instruction = line.first()
        val num = line.substring(1).toInt()

        when (instruction) {
            'N' -> { posY -= num }
            'E' -> { posX += num }
            'W' -> { posX -= num }
            'S' -> { posY += num }
            'L' -> { face = ((face + num / 90 + 4).rem(4) + 4).rem(4) }
            'R' -> { face = ((face - num / 90 + 4).rem(4) + 4).rem(4) }
            'F' -> {
                when (face) {
                    0 -> posX += num
                    1 -> posY -= num
                    2 -> posX -= num
                    3 -> posY += num
                }
            }
        }
    }

    print(abs(posX) + abs(posY))
}
