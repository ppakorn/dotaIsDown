import java.util.*

class SolutionSPK {
    fun numSubarrayProductLessThanK(nums: IntArray, k: Int): Int {
        var count = 0
        var i = 0
        var j = 0
        var product = 1

        while (i < nums.size) {
            if (j < i) {
                j = i
            }

            while (j < nums.size && product * nums[j] < k) {
                product *= nums[j]
                j += 1
            }

            count += (j - i)
            product = maxOf(1, product / nums[i])
            i += 1
        }

        return count
    }
}

fun main() {
    val s = SolutionSPK()
    println(s.numSubarrayProductLessThanK(intArrayOf(10, 5, 2, 6), 100))
    println(s.numSubarrayProductLessThanK(intArrayOf(10, 5, 2, 80, 6), 100))
    println(s.numSubarrayProductLessThanK(intArrayOf(10, 5, 2, 80, 6), 0))
    println(s.numSubarrayProductLessThanK(intArrayOf(10, 5, 2, 80, 6), 3))
}