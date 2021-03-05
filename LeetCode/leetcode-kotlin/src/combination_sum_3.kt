class SolutionCB3 {
    fun combinationSum3(k: Int, n: Int): List<List<Int>> {
        if (n > 45) return emptyList()
        val result = mutableListOf<List<Int>>()
        combination(k, n, 0, listOf<Int>(), result)
        return result
    }

    private fun combination(k: Int, n: Int, previous: Int, l: List<Int>, result: MutableList<List<Int>>) {
        if (k == 1 && n > previous && n <= 9) {
            result.add(l + n)
        }

        if (n <= previous) return

        (previous+1..9).forEach {
            combination(k - 1, n - it, it, l + it, result)
        }
    }
}

fun main() {
    val s = SolutionCB3()
    println(s.combinationSum3(3, 9))
}