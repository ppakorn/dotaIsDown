class SolutionPKS {
    fun pancakeSort(A: IntArray): List<Int> {
        val result = mutableListOf<Int>()
        var n = A.size
        var x = A
        while (n > 0) {
            val maxIndex = x.indexOf(n)
            if (maxIndex == n - 1) {
                n -= 1
                continue
            }
            x = sort(x, maxIndex, result)
            x = sort(x, n - 1, result)
            n -= 1
        }
        return result
    }

    private fun sort(A: IntArray, index: Int, result: MutableList<Int>): IntArray {
        val temp = A.copyOf()
        for (i in 0..index) {
            temp[i] = A[index - i]
        }
        result.add(index + 1)
        return temp
    }
}

fun main() {
    val s = SolutionPKS()
    println(s.pancakeSort(intArrayOf(3, 2, 4, 1)))
    println(s.pancakeSort(intArrayOf(1, 2, 3)))
}