package leetcode

import kotlin.math.max

class Solution1376 {
    fun numOfMinutes(n: Int, headID: Int, manager: IntArray, informTime: IntArray): Int {
        val subordinates = mutableMapOf<Int, MutableList<Int>>()
        manager.forEachIndexed { sub, m ->
            if (subordinates[m] == null) {
                subordinates[m] = mutableListOf(sub)
            } else {
                subordinates[m]!!.add(sub)
            }
        }

        return recur(0, headID, subordinates, informTime)
    }

    fun recur(
        currentMinute: Int,
        currentManager: Int,
        subordinates: Map<Int, List<Int>>,
        informTime: IntArray
    ): Int {
        val subs = subordinates[currentManager]
        if (subs.isNullOrEmpty()) {
            return currentMinute
        }

        var max = currentMinute
        subs.forEach {
            val a = recur(currentMinute + informTime[currentManager], it, subordinates, informTime)
            max = max(max, a)
        }

        return max
    }
}

fun main() {
    val s = Solution1376()
    val n = 6
    val headID = 2
    val manager = intArrayOf(2,2,-1,2,2,2)
    val informTime = intArrayOf(0,0,1,0,0,0)
    println(s.numOfMinutes(n, headID, manager, informTime))
}