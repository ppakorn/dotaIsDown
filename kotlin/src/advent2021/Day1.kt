package advent2021

import java.io.File

class Day1 {

    fun execute() {
        val a = mutableListOf<Int>()
        File("/Users/ampos/Documents/dota_is_down/kotlin/src/advent2021/Day1.input").forEachLine { line ->
            a.add(line.toInt())
        }

        var count = 0
        a.forEachIndexed { i, v ->
            if (i + 3 == a.count()) {
                print(count)
                return
            }

            if (v < a[i + 3]) {
                count += 1
            }
        }
    }

}

fun main() {
    val d = Day1()
    d.execute()
}