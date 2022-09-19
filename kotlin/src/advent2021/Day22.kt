package advent2021

import java.io.File

class Day22 {
    private val filename = "/Users/ampos/Documents/dota_is_down/kotlin/src/advent2021/Day22-test.input"

    private val on: MutableMap<Int, MutableMap<Int, MutableSet<Int>>> = mutableMapOf()

    fun execute() {
        File(filename).forEachLine { line ->
            println(line)
            val a = line.split(" ")
            val onOrOff = a[0]
            val b = a[1].split(",")
            val x = b[0].split("=")[1].split("..").map { it.toInt() }
            val y = b[1].split("=")[1].split("..").map { it.toInt() }
            val z = b[2].split("=")[1].split("..").map { it.toInt() }
            if (onOrOff == "on") {
                processOn(x, y, z)
            } else {
                processOff(x, y, z)
            }
        }
    }

    private fun processOn(x: List<Int>, y: List<Int>, z: List<Int>) {
        for (i in x[0] until x[1] + 1) {
            for (j in y[0] until y[1] + 1) {
                for (k in z[0] until z[1] + 1) {
                    if (on[i] == null) {
                        on[i] = mutableMapOf()
                    }
                    if (on[i]!![j] == null) {
                        on[i]!![j] = mutableSetOf()
                    }
                    on[i]!![j]!!.add(k)
                }
            }
        }
    }

    private fun processOff(x: List<Int>, y: List<Int>, z: List<Int>) {
        for (i in x[0] until x[1] + 1) {
            for (j in y[0] until y[1] + 1) {
                for (k in z[0] until z[1] + 1) {
                    on[i]?.get(j)?.remove(k)
                }
            }
        }
    }

    fun count() {
        val count = on.values.sumOf { y ->
            y.values.sumOf { it.size.toLong() }
        }
        println(count)
    }
}

private class Cuboid(
    val xRange: IntRange,
    val yRange: IntRange,
    val zRange: IntRange
) {
    // Is this a subset of another, this <= another
    fun isSubset(another: Cuboid): Boolean {
        return xRange.first in another.xRange && xRange.last in another.xRange &&
            yRange.first in another.yRange && yRange.last in another.yRange &&
            zRange.first in another.zRange && zRange.last in another.zRange
    }

    fun isOverlapX(another: Cuboid): Boolean =
        xRange.first in another.xRange || xRange.last in another.xRange
    fun isOverlapY(another: Cuboid): Boolean =
        yRange.first in another.yRange || yRange.last in another.yRange
    fun isOverlapZ(another: Cuboid): Boolean =
        zRange.first in another.zRange || zRange.last in another.zRange

    fun processOverlap(another: Cuboid): Set<Cuboid> {

    }

    override fun toString(): String {
        return "[x = (${xRange.first}, ${xRange.last}), y = (${yRange.first}, ${yRange.last}), z = (${zRange.first}, ${zRange.last})"
    }
}

fun main() {
    val d = Day22()
    d.execute()
    d.count()
}