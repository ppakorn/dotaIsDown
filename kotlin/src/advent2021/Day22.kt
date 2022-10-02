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

private enum class Axis {
    X, Y, Z
}

private data class Cuboid(
    val xRange: IntRange,
    val yRange: IntRange,
    val zRange: IntRange
) {
    // Is this a subset of another, this <= another
    fun isSubsetOf(another: Cuboid): Boolean {
        return xRange.first in another.xRange && xRange.last in another.xRange &&
            yRange.first in another.yRange && yRange.last in another.yRange &&
            zRange.first in another.zRange && zRange.last in another.zRange
    }

    private fun isOverlapX(another: Cuboid): Boolean =
        xRange.first in another.xRange || xRange.last in another.xRange ||
            another.xRange.first in xRange || another.xRange.last in xRange
    private fun isOverlapY(another: Cuboid): Boolean =
        yRange.first in another.yRange || yRange.last in another.yRange ||
            another.yRange.first in yRange || another.yRange.last in yRange
    private fun isOverlapZ(another: Cuboid): Boolean =
        zRange.first in another.zRange || zRange.last in another.zRange ||
            another.zRange.first in zRange || another.zRange.last in zRange

    fun isOverlap(another: Cuboid): Boolean = isOverlapX(another) && isOverlapY(another) && isOverlapZ(another)

    // Assume both are not subset of each other
    // Return (overlap cuboid, non-overlap sub-cuboids of another)
    fun processOverlap(another: Cuboid): Pair<Cuboid, List<Cuboid>> {
        val nonOverlaps = mutableListOf<Cuboid>()

        val newX = processOverlap1Axis(another, Axis.X)
        val newXOverlap = newX.filter { isOverlap(it) }
        val newXNotOverlap = newX.filter { !isOverlap(it) }
        nonOverlaps.addAll(newXNotOverlap)

        val newY = newXOverlap.flatMap {
            processOverlap1Axis(it, Axis.Y)
        }
        val newYOverlap = newY.filter { isOverlap(it) }
        val newYNotOverlap = newY.filter { !isOverlap(it) }
        nonOverlaps.addAll(newYNotOverlap)

        val newZ = newYOverlap.flatMap {
            processOverlap1Axis(it, Axis.Z)
        }
        val newZOverlap = newZ.filter { isOverlap(it) }
        val newZNotOverlap = newZ.filter { !isOverlap(it) }
        nonOverlaps.addAll(newZNotOverlap)

        assert(newZOverlap.size == 1)
        return Pair(newZOverlap[0], nonOverlaps)
    }

//    private fun processOverlapX(another: Cuboid): List<Cuboid> {
//        val newXRanges = mutableListOf<IntRange>()
//        if (another.xRange.first < xRange.first) {
//            newXRanges.add(IntRange(another.xRange.first, xRange.first - 1))
//            if (another.xRange.last <= xRange.last) {
//                newXRanges.add(IntRange(xRange.first, another.xRange.last))
//            } else {
//                newXRanges.add(xRange)
//                newXRanges.add(IntRange(xRange.last + 1, another.xRange.last))
//            }
//        } else {
//            if (another.xRange.last <= xRange.last) {
//                newXRanges.add(another.xRange)
//            } else {
//                newXRanges.add(IntRange(another.xRange.first, xRange.last))
//                newXRanges.add(IntRange(xRange.last + 1, another.xRange.last))
//            }
//        }
//
//        val newAnotherCuboids = newXRanges.map {
//            Cuboid(it, another.yRange, another.zRange)
//        }
//        return newAnotherCuboids
//    }

    private fun processOverlap1Axis(another: Cuboid, axis: Axis): List<Cuboid> {
        val old = when (axis) {
            Axis.X -> xRange
            Axis.Y -> yRange
            Axis.Z -> zRange
        }
        val new = when (axis) {
            Axis.X -> another.xRange
            Axis.Y -> another.yRange
            Axis.Z -> another.zRange
        }
        val newRanges = mutableListOf<IntRange>()

        if (new.first < old.first) {
            newRanges.add(IntRange(new.first, old.first - 1))
            if (new.last <= old.last) {
                newRanges.add(IntRange(old.first, new.last))
            } else {
                newRanges.add(old)
                newRanges.add(IntRange(old.last + 1, new.last))
            }
        } else {
            if (new.last <= old.last) {
                newRanges.add(new)
            } else {
                newRanges.add(IntRange(new.first, old.last))
                newRanges.add(IntRange(old.last + 1, new.last))
            }
        }

        val newAnotherCuboids = newRanges.map {
            when (axis) {
                Axis.X -> Cuboid(it, another.yRange, another.zRange)
                Axis.Y -> Cuboid(another.xRange, it, another.zRange)
                Axis.Z -> Cuboid(another.xRange, another.yRange, it)
            }
        }
        return newAnotherCuboids
    }

    fun clone(): Cuboid = Cuboid(xRange, yRange, zRange)

    fun count(): Long {
        return xRange.count().toLong() * yRange.count().toLong() * zRange.count().toLong()
    }

    override fun toString(): String {
        return "[x = (${xRange.first}, ${xRange.last}), y = (${yRange.first}, ${yRange.last}), z = (${zRange.first}, ${zRange.last})"
    }
}

class Day22_2 {
    private val filename = "/Users/ampos/Documents/dota_is_down/kotlin/src/advent2021/Day22.input"
    private val nonOverlapOns = mutableListOf<Cuboid>()

    fun execute() {
        File(filename).forEachLine forEachLine@ { line ->
            println(line)
            val a = line.split(" ")
            val onOrOff = a[0]
            val b = a[1].split(",")
            val x = b[0].split("=")[1].split("..").map { it.toInt() }
            val y = b[1].split("=")[1].split("..").map { it.toInt() }
            val z = b[2].split("=")[1].split("..").map { it.toInt() }

            val newCuboid = Cuboid(
                IntRange(x[0], x[1]), IntRange(y[0], y[1]), IntRange(z[0], z[1])
            )

            if (nonOverlapOns.isEmpty()) {
                nonOverlapOns.add(newCuboid)
                return@forEachLine
            }

            if (onOrOff == "on") {
                processOn(newCuboid)
            } else {
                processOff(newCuboid)
            }
        }
    }

    private fun processOn(newCuboid: Cuboid) {
        val toProcess = mutableSetOf(newCuboid)
        while (toProcess.isNotEmpty()) {
            val new = toProcess.first()
            val toReprocess = mutableListOf<Cuboid>()
            val toRemove = mutableListOf<Cuboid>()
            val toAdd = mutableListOf<Cuboid>()

            run r@ {
                var noOverlap = true
                nonOverlapOns.forEach { old ->
                    if (old.isSubsetOf(new)) {
                        noOverlap = false
                        toRemove.add(old)
                        toReprocess.add(new)
                    } else if (new.isSubsetOf(old)) {
                        noOverlap = false
                        // break
                        return@r
                    } else if (old.isOverlap(new)) {
                        noOverlap = false
                        val resultFromOverlap = old.processOverlap(new)
                        toReprocess.addAll(resultFromOverlap.second)
                        // break
                        return@r
                    }
                }

                if (noOverlap) {
                    toAdd.add(new)
                }
            }

            toProcess.remove(new)
            toProcess.addAll(toReprocess)
            nonOverlapOns.removeAll(toRemove)
            nonOverlapOns.addAll(toAdd)
        }
    }

    private fun processOff(off: Cuboid) {
        val toRemove = mutableListOf<Cuboid>()
        val toAdd = mutableListOf<Cuboid>()
        nonOverlapOns.forEach {
            if (it.isSubsetOf(off)) {
                toRemove.add(it)
            } else if (it.isOverlap(off)) {
                toRemove.add(it)
                val resultFromOverlap = off.processOverlap(it)
                toAdd.addAll(resultFromOverlap.second)
            }
        }
        nonOverlapOns.removeAll(toRemove)
        nonOverlapOns.addAll(toAdd)
    }

    fun count(): Long {
        return nonOverlapOns.sumOf { it.count() }
    }
}

fun main() {
    val d = Day22_2()
    d.execute()
    println(d.count())

//    val c0 = Cuboid(IntRange(10, 11), IntRange(10, 11), IntRange(10, 11))
//    val c1 = Cuboid(IntRange(10, 12), IntRange(10, 12), IntRange(10, 12))
//    val c2 = Cuboid(IntRange(10, 13), IntRange(11, 13), IntRange(11, 13))
//    val r = c1.processOverlap(c0)
//    println(r.first.count())
//    println(r.second.sumOf { it.count() })
//    println(r)
}