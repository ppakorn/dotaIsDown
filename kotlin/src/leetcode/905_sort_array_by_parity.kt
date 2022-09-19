class Solution905 {
    fun sortArrayByParity(A: IntArray): IntArray {
        val result = IntArray(A.size)
        var i = 0
        var j = result.lastIndex
        A.forEach {
            if (it % 2 == 0) {
                result[i] = it
                i += 1
            } else {
                result[j] = it
                j -= 1
            }
        }
        return result
    }
}

fun main() {
    val s = Solution905()
    println(s.sortArrayByParity(intArrayOf(3,1,2,4)).contentToString())
    println(s.sortArrayByParity(intArrayOf()).contentToString())
    println(s.sortArrayByParity(intArrayOf(1,2)).contentToString())
    println(s.sortArrayByParity(intArrayOf(1)).contentToString())
}