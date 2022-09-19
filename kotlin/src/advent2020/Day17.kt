package advent2020

class Day17 {

//    private val starter = listOf(
//        ".#.",
//        "..#",
//        "###"
//    )
    private val starter = listOf(
        "##......",
        ".##...#.",
        ".#######",
        "..###.##",
        ".#.###..",
        "..#.####",
        "##.####.",
        "##..#.##"
    )

    private val maxCycle = 6
    private val size = starter.count()
    private val zLayer = maxCycle * 2 + 1
    private val xLayer = size + maxCycle * 2
    private var mem = newMem()

    fun main() {

        for (i in 0 until size) {
            for (j in 0 until size) {
                mem[maxCycle][maxCycle][i + maxCycle][j + maxCycle] = starter[i][j]
            }
        }

        for (cycle in 1..maxCycle) {
            val min = maxCycle - cycle
            val max = maxCycle + cycle
            val newMem = newMem()
            for (h in min..max) {
                for (i in min..max) {
                    for (j in min until max + size) {
                        for (k in min until max + size) {
                            val count = countActiveAround(h, i, j, k)
                            when (mem[h][i][j][k]) {
                                '.' -> {
                                    if (count == 3) {
                                        newMem[h][i][j][k] = '#'
                                    }
                                }
                                '#' -> {
                                    if (count < 2 || count > 3) {
                                        newMem[h][i][j][k] = '.'
                                    } else {
                                        newMem[h][i][j][k] = '#'
                                    }
                                }
                            }
                        }
                    }
                }
            }
            mem = newMem
        }
        println(countAllActiveFromMem())
        printLayer(0, 0)
        println()
        printLayer(0, 1)
        println()
        printLayer(0, 2)
    }

    private fun newMem(): Array<Array<Array<Array<Char>>>> {
        return Array(zLayer) { Array(zLayer) { Array(xLayer) { Array(xLayer) { '.' } } } }
    }

    private fun printLayer(k: Int, l: Int) {
        for (i in mem[k][l]) {
            for (j in i) {
                print(j)
            }
            println()
        }
    }

    private fun countActiveAround(w: Int, z: Int, x: Int, y: Int): Int {
        var count = 0
        for (h in -1..1) {
            if (w + h < 0 || w + h >= zLayer) continue
            for (i in -1..1) {
                if (z + i < 0 || z + i >= zLayer) continue
                for (j in -1..1) {
                    if (x + j < 0 || x + j >= xLayer) continue
                    for (k in -1..1) {
                        if (y + k < 0 || y + k >= xLayer) continue
                        if (h==0 && i == 0 && j == 0 && k == 0) {
                            continue
                        }
                        if (mem[w + h][z + i][x + j][y + k] == '#') {
                            count += 1
                        }
                    }
                }
            }
        }
        return count
    }

    private fun countAllActiveFromMem(): Int {
        var count = 0
        mem.forEach { i ->
            i.forEach { j ->
                j.forEach { k ->
                    k.forEach { l ->
                        if (l == '#') count += 1
                    }
                }
            }
        }
        return count
    }

}

fun main() {
    val d = Day17()
    d.main()
}