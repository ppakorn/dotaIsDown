package advent2021

import java.io.File

class Day12 {
    private val paths = mutableMapOf<String, MutableList<String>>()

    fun execute() {
        paths.clear()
        File("/Users/ampos/Documents/dota_is_down/kotlin/src/advent2021/Day12.input").forEachLine { line ->
            val a = line.split('-')
            insertPath(a[0], a[1])
        }

        println(travel2("start", emptySet(), false))
    }

    private fun insertPath(point1: String, point2: String) {
        if (point2 != "start") {
            if (paths[point1] == null) {
                paths[point1] = mutableListOf(point2)
            } else {
                paths[point1]!!.add(point2)
            }
        }

        if (point1 != "start") {
            if (paths[point2] == null) {
                paths[point2] = mutableListOf(point1)
            } else {
                paths[point2]!!.add(point1)
            }
        }
    }

//    private fun travel(current: String, visited: Set<String>): Int {
//        if (current == "end") {
//            return 1
//        }
//
//        val newVisited = if (current[0] >= 'a') {
//            visited + current
//        } else {
//            visited
//        }
//
//        var count = 0
//        val nexts = paths[current]
//        nexts?.forEach { next ->
//            if (next[0] >= 'a' && visited.contains(next)) {
//                return@forEach
//            }
//            count += travel(next, newVisited)
//        }
//        return count
//    }

    private fun travel2(current: String, visited: Set<String>, smallTwice: Boolean): Int {
        if (current == "end") {
            return 1
        }

        val newVisited = if (current[0] >= 'a') {
            visited + current
        } else {
            visited
        }

        var count = 0
        val nexts = paths[current]
        nexts?.forEach { next ->
            if (
                next[0] < 'a' ||
                (next[0] >= 'a' && !visited.contains(next))
            ) {
                count += travel2(next, newVisited, smallTwice)
            } else if (next[0] >= 'a' && visited.contains(next) && !smallTwice) {
                count += travel2(next, newVisited, true)
            }
        }
        return count
    }
}

fun main() {
    val d = Day12()
    d.execute()
}