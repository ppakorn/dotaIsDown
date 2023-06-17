package leetcode

import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sqrt

class Solution2101 {
    fun maximumDetonation(bombs: Array<IntArray>): Int {
        val map = mutableMapOf<Int, MutableSet<Int>>()
        bombs.forEachIndexed { i, spec ->
            map[i] = mutableSetOf(i)
            bombs.forEachIndexed j@{ j, spec2 ->
                if (i == j) {
                    return@j
                }

                val distanceX = spec[0] - spec2[0]
                val distanceY = spec[1] - spec2[1]
                val distance = sqrt(distanceX.toDouble().pow(2) + distanceY.toDouble().pow(2))
                if (distance <= spec[2]) {
                    map[i]!!.add(j)
                }
            }
        }

        map.keys.forEach {
            chainReaction(bombs, it, map)
        }

        var max = Int.MIN_VALUE
        map.values.forEach { max = max(max, it.size) }

        return max
    }

    private fun chainReaction(bombs: Array<IntArray>, bomb: Int, map: Map<Int, MutableSet<Int>>) {
        val visited = BooleanArray(bombs.size) { false }
        val stack = mutableListOf(bomb)
        while (stack.isNotEmpty()) {
            val i = stack[stack.lastIndex]
            stack.removeAt(stack.lastIndex)
            if (visited[i]) {
                continue
            }

            visited[i] = true
            map[bomb]!!.addAll(map[i]!!)
            stack.addAll(map[i]!!)
        }
    }

    fun transformInput(str: String): Array<IntArray> {
        return str
            .removePrefix("[")
            .removeSuffix("]")
            .split("],")
            .map { a ->
                val aa = a.removePrefix("[").removeSuffix("]")
                if (aa.isEmpty()) {
                    emptyList<Int>().toIntArray()
                } else {
                    aa.split(",").map { it.toInt() }.toIntArray()
                }
            }
            .toTypedArray()
    }
}

fun main() {
    val s = Solution2101()
    var input = s.transformInput("[[2,1,3],[6,1,4]]")
    println(s.maximumDetonation(input))
    input = s.transformInput("[[1,2,3],[2,3,1],[3,4,2],[4,5,3],[5,6,4]]")
    println(s.maximumDetonation(input))
}