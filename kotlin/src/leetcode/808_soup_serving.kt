package leetcode

class Solution808 {
    fun soupServings(n: Int): Double {
        if (n >= 4500) return 1.0
        mem.clear()
        return recur(n, n)
    }

    private val mem = mutableMapOf<Pair<Int, Int>, Double>()
    private fun recur(a: Int, b: Int): Double {
        if (a <= 0 && b <= 0) return 0.5
        if (a <= 0) return 1.0
        if (b <= 0) return 0.0

        val p = Pair(a, b)
        if (mem[p] != null) return mem[p]!!

        val result = 0.25 * (
            recur(a - 100, b) +
            recur(a - 75, b - 25) +
            recur(a - 50, b - 50) +
            recur(a - 25, b - 75)
        )
        mem[p] = result
        return result
    }
}

fun main() {
    val s = Solution808()
    println(s.soupServings(1000))
}