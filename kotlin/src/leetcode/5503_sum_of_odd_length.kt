class Solution5503 {
    fun sumOddLengthSubarrays(arr: IntArray): Int {
        var len = 1
        var sum = 0
        while (len <= arr.size) {
            (0..(arr.size - len)).forEach { start ->
                val sub = arr.slice(start..(start+len-1))
                sum += sub.sum()
            }
            len += 2
        }

        return sum
    }
}

fun main() {
    val s = Solution5503()
    println(s.sumOddLengthSubarrays(intArrayOf(10,11,12)))
}