package advent2020

import java.io.File

fun main() {
    File("/Users/ampos/Documents/dota_is_down/LeetCode/leetcode-kotlin/src/advent2020/Day13.input").forEachLine { line ->
        val ids = line.split(',')
            .mapIndexed { index, s -> Pair(index, s) }
            .filter { it.second != "x" }
            .map {
                val id = it.second.toLong()
                val index = it.first.toLong()
                val remainder = ((id - index).rem(id) + id).rem(id)
                Pair(remainder, id)
            }
            .sortedByDescending { it.second }
        execute(ids)
    }
}

fun execute(ids: List<Pair<Long, Long>>) {
    val max = ids[0].second
//    var x = max + ids[0].first
    var x = 100000000000850
    val l = ids.count()
    while (true) {
        var thisIsIt = true
        for (i in 1 until l) {
            val remainder = ids[i].first
            val id = ids[i].second
            if (x.rem(id) != remainder) {
                x += max
                thisIsIt = false
                break
            }
        }

        if (thisIsIt) {
            println(x)
            break
        }

        if (x < 0) {
            break
        }
    }

//    Answer is 690123192779524, Use Chinese Remainder Theorem
}