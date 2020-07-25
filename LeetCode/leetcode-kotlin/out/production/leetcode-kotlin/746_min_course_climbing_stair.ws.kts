class Solution746 {
    fun minCostClimbingStairs(cost: IntArray): Int {
        var min0 = 0
        var min1 = 0
        cost.forEach { c ->
            val new = minOf(min0, min1) + c
            min0 = min1
            min1 = new
        }
        return minOf(min0, min1)
    }
}

val s = Solution746()
s.minCostClimbingStairs(intArrayOf(1, 2, 2, 1))