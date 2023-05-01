package advent2021

import java.io.File

class Day6 {
    fun execute() {
        var fish = Array(9) { 0L }
        File("/Users/ampos/Documents/dota_is_down/kotlin/src/advent2021/Day6.input").forEachLine { line ->
            val a = line.split(",")
            a.forEach {
                fish[it.toInt()] = fish[it.toInt()] + 1
            }
        }

        val days = 256
        (1..days).forEach { _ ->
            val newFish = Array(9) { 0L }
            (1..8).forEach { i ->
                newFish[i - 1] = fish[i]
            }
            newFish[8] = fish[0]
            newFish[6] += fish[0]
            fish = newFish
        }

        println(fish.sum())
    }

    private fun printFish(fish: Array<Int>) {
        fish.forEach {
            print("$it ")
        }
    }
}

fun main() {
    val d = Day6()
    d.execute()
}