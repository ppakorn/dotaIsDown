class Solution136 {
    fun singleNumber(nums: IntArray): Int {
        return nums.fold(0) { acc, n -> acc xor n }
    }
}

fun main() {
    val s = Solution136()
    println(s.singleNumber(intArrayOf(4,1,2,1,2)))
}