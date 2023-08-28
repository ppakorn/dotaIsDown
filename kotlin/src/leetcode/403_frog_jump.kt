package leetcode

class Solution403 {
    private val failed = mutableSetOf<String>()
    fun canCross(stones: IntArray): Boolean {
        failed.clear()
        return f(stones, 0, 0)
    }

    private fun f(stones: IntArray, i: Int, k: Int): Boolean {
        if (i == stones.size - 1) {
            return true
        }

        val key = key(i, k)
        if (failed.contains(key)) {
            return false
        }

        var j = 1
        while (i + j < stones.size && stones[i + j] - stones[i] <= k + 1) {
            val dif = stones[i + j] - stones[i]
            if (dif == k || dif == k - 1 || dif == k + 1) {
                if (f(stones, i + j, dif)) {
                    return true
                }
            }
            j += 1
        }

        failed.add(key)
        return false
    }

    private fun key(i: Int, k: Int): String {
        return "$i-$k"
    }
}

fun main() {
    val s = Solution403()
    println(s.canCross(intArrayOf(0,1,3,6,7)))
}