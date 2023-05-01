class Solution442 {
    fun findDuplicates(nums: IntArray): List<Int> {
        val len = nums.size
        nums.forEach {
            val i = (it - 1) % len
            nums[i] += len
        }

        val result = mutableListOf<Int>()
        nums.forEachIndexed { i, n ->
            if (n > 2 * len) {
                result.add(i + 1)
            }
        }

        return result
    }
}

fun main() {
    val s = Solution442()
    println(s.findDuplicates(intArrayOf(4,3,2,8,7,2,3,1)))
    println(s.findDuplicates(intArrayOf(1)))
    println(s.findDuplicates(intArrayOf()))
    println(s.findDuplicates(intArrayOf(4,5,7,8,2,3,1,6)))
}