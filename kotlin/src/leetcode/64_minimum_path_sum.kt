package leetcode

import kotlin.math.min

class Solution64 {
    fun minPathSum(grid: Array<IntArray>): Int {
        var mem = mutableListOf<Int>()
        grid.forEach { row ->
            if (mem.isEmpty()) {
                row.forEach { i ->
                    if (mem.isEmpty()) {
                        mem.add(grid[0][0])
                    } else {
                        mem.add(mem.last() + i)
                    }
                }
                return@forEach
            }

            val newMem = mutableListOf<Int>()
            row.forEachIndexed { index, i ->
                val a = when (index) {
                    0 -> {
                        mem[0] + i
                    }
                    else -> {
                        min(mem[index], newMem.last()) + i
                    }
                }
                newMem.add(a)
            }
            mem = newMem
        }

        return mem.last()
    }
}

fun main() {
    val s = Solution64()
    val input = transform2DIntArray("[[1,2,3],[4,5,6]]")
    println(s.minPathSum(input))
}