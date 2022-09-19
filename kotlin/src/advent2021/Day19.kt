package advent2021

import java.io.File
import java.lang.Integer.max
import kotlin.math.abs
import kotlin.math.sign

class Day19 {
    private val initialMasks = listOf(
        listOf(1, 2, 3),
        listOf(-1, 2, -3),
        listOf(2, 1, -3),
        listOf(-2, 1, 3),
        listOf(3, 1, 2),
        listOf(-3, 1, -2)
    )
    private var masks: List<List<Int>> = emptyList()
    private val map: MutableSet<List<Int>> = mutableSetOf()
    private val collectedScanners: MutableList<List<Int>> = mutableListOf()
    private val filename = "/Users/ampos/Documents/dota_is_down/kotlin/src/advent2021/Day19.input"


    private fun createMasks(initial: List<Int>): List<List<Int>> {
        return listOf(
            initial,
            listOf(initial[0], -initial[1], -initial[2]),
            listOf(initial[0], initial[2], -initial[1]),
            listOf(initial[0], -initial[2], initial[1]),
        )
    }

    init {
        masks = initialMasks.map { createMasks(it) }.flatten()
    }

    private fun rotate(beacon: List<Int>, mask: List<Int>): List<Int> {
        return listOf(
            beacon[abs(mask[0]) - 1] * mask[0].sign,
            beacon[abs(mask[1]) - 1] * mask[1].sign,
            beacon[abs(mask[2]) - 1] * mask[2].sign,
        )
    }

    fun testRotate() {
        val beacons = listOf(
            listOf(-1, -1, 1),
            listOf(-2, -2, 2),
            listOf(-3, -3, 3),
            listOf(-2, -3, 1),
            listOf(5, 6, -4),
            listOf(8, 0, 7),
        )
        val expected = listOf(
            listOf(
                listOf(1,-1,1),
                listOf(2,-2,2),
                listOf(3,-3,3),
                listOf(2,-1,3),
                listOf(-5,4,-6),
                listOf(-8,-7,0),
            ),
            listOf(
                listOf(-1,-1,-1),
                listOf(-2,-2,-2),
                listOf(-3,-3,-3),
                listOf(-1,-3,-2),
                listOf(4,6,5),
                listOf(-7,0,8),
            ),
            listOf(
                listOf(1,1,-1),
                listOf(2,2,-2),
                listOf(3,3,-3),
                listOf(1,3,-2),
                listOf(-4,-6,5),
                listOf(7,0,8),
            ),
            listOf(
                listOf(1,1,1),
                listOf(2,2,2),
                listOf(3,3,3),
                listOf(3,1,2),
                listOf(-6,-4,-5),
                listOf(0,7,-8),
            )
        )

        val results = masks.map { mask ->
            beacons.map { b ->
                rotate(b, mask)
            }
        }

        expected.forEach {
            assert(it in results)
        }
    }

    private fun readInput(): List<List<List<Int>>> {
        val scanners = mutableListOf<List<List<Int>>>()
        var scanner = mutableListOf<List<Int>>()
        File(filename).forEachLine { line ->
            if (line.isEmpty())
                return@forEachLine
            if (line.startsWith("---")) {
                scanners.add(scanner)
                scanner = mutableListOf()
                return@forEachLine
            }

            val b = line.split(",").map { it.toInt() }
            scanner.add(b)
        }

        scanners.add(scanner)
        scanners.removeAt(0)
        return scanners
    }

    private fun process(scanner: List<List<Int>>): Boolean {
        if (map.isEmpty()) {
            map.addAll(scanner)
            return true
        }

        for (mask in masks) {
            val beacons = scanner.map { rotate(it, mask) }
            for (oldBeacon in map) {
                for (newBeacon in beacons) {
                    val delta = oldBeacon.mapIndexed { index, i -> i - newBeacon[index] }
                    val transformedNewBeacons = transform(beacons, delta).toSet()
                    val overlap = map.intersect(transformedNewBeacons)
                    if (overlap.size >= 12) {
                        collectedScanners.add(delta)
                        map.addAll(transformedNewBeacons)
                        return true
                    }
                }
            }
        }

        return false
    }

    private fun transform(beacons: List<List<Int>>, delta: List<Int>): List<List<Int>> {
        return beacons.map { beacon ->
            beacon.mapIndexed { index, i -> i + delta[index] }
        }
    }

    fun execute() {
        val scanners = readInput().toMutableList()

        while (scanners.size > 0) {
            println("scanners.size = ${scanners.size}")
            println("map.size = ${map.size}")
            run breaking@ {
                scanners.forEach { scanner ->
                    if (process(scanner)) {
                        scanners.remove(scanner)
                        return@breaking
                    }
                }
                throw Exception("No scanner added to map")
            }
        }

        println(map.size)
        println(collectedScanners)
    }

    fun execute2() {
        var max = 0
        for (i in 0 until collectedScanners.size - 1) {
            for (j in i + 1 until collectedScanners.size) {
                val distance = manhattan(collectedScanners[i], collectedScanners[j])
                max = max(max, distance)
            }
        }
        println(max)
    }

    private fun manhattan(point1: List<Int>, point2: List<Int>): Int {
        return abs(point1[0] - point2[0]) +
            abs(point1[1] - point2[1]) +
            abs(point1[2] - point2[2])
    }
}

fun main() {
    val d = Day19()
    d.execute()
    d.execute2()
}