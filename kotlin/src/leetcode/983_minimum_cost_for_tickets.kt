import kotlin.test.assertEquals

class Solution983 {
    fun mincostTickets(days: IntArray, costs: IntArray): Int {
        val totalCosts = IntArray(days.last() + 1) {0}
        var lastCost = 0
        var nextDayIndex = 0
        for (i in days[0] until totalCosts.size) {
            if (i < days[nextDayIndex]) {
                totalCosts[i] = lastCost
                continue
            }

            val cost1 = costs[0] + totalCosts[i - 1]
            val cost7 = if (i - 7 < 0) {
                costs[1]
            } else {
                costs[1] + totalCosts[i - 7]
            }
            val cost30 = if (i - 30 < 0) {
                costs[2]
            } else {
                costs[2] + totalCosts[i - 30]
            }
            totalCosts[i] = minOf(cost1, cost7, cost30)
            lastCost = totalCosts[i]
            nextDayIndex += 1
        }
        return totalCosts.last()
    }
}

fun main() {
    val s = Solution983()
    assertEquals(9, s.mincostTickets(intArrayOf(1,4,5,6,7,9), intArrayOf(2,7,15)))
    assertEquals(11, s.mincostTickets(intArrayOf(1,4,6,7,8,20), intArrayOf(2,7,15)))
    assertEquals(17, s.mincostTickets(intArrayOf(1,2,3,4,5,6,7,8,9,10,30,31), intArrayOf(2,7,15)))
//    assertEquals(9, s.mincostTickets(intArrayOf(1,4,5,6,7,9), intArrayOf(2,7,15)))
}