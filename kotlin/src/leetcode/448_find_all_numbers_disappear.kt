import kotlin.math.abs

class Solution448 {
    fun findDisappearedNumbers(nums: IntArray): List<Int> {
        nums.forEach { n ->
            val i = abs(n) - 1
            nums[i] = abs(nums[i]) * (-1)
        }

        val result = mutableListOf<Int>()
        nums.forEachIndexed { i, n ->
            if (n > 0) {
                result.add(i + 1)
            }
        }

        return result
    }
}

fun main() {
    val s = Solution448()
    println(s.findDisappearedNumbers(intArrayOf(4,3,2,7,8,2,3,1)))
    println(s.findDisappearedNumbers(intArrayOf(1)))
    println(s.findDisappearedNumbers(intArrayOf()))
}