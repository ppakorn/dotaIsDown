class Solution494 {
    fun findTargetSumWays(nums: IntArray, S: Int): Int {
        var map = mutableMapOf(0 to 1)
        nums.forEach { n ->
            val temp = map
            map = mutableMapOf()
            temp.forEach {
                map[it.key + n] = (map[it.key + n] ?: 0) + it.value
                map[it.key - n] = (map[it.key - n] ?: 0) + it.value
            }
        }

        return map[S] ?: 0
    }
}

fun main() {
    val s = Solution494()
    println(s.findTargetSumWays(intArrayOf(1,1,1), 2))
    println(s.findTargetSumWays(intArrayOf(1,1,1,1,1), 3))
    listOf(1,2,3).distinct()
}