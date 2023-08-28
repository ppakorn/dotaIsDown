package leetcode

class Solution77 {
    fun combine(n: Int, k: Int): List<List<Int>> {
        return recur(listOf(listOf()), n, k, 0)
    }

    private fun recur(inProgress: List<List<Int>>, n: Int, k: Int, previous: Int): List<List<Int>> {
        if (inProgress[0].size == k) {
            return inProgress
        }

        val result = mutableListOf<List<Int>>()
        for (i in previous + 1 .. n) {
            val newProgress = inProgress.map { it + i }
            result.addAll(recur(newProgress, n, k, i))
        }

        return result
    }
}

fun main() {
    val s = Solution77()
    println(s.combine(4, 3))
}
