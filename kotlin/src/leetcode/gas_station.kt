import kotlin.test.assertEquals

class SolutionGST {
    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        var cumulativeLost = 0
        var cumulativeProfit = 0
        var start = 0
        gas.indices.forEach { i ->
            val g = gas[i]
            val c = cost[i]
            val profit = g - c

            cumulativeProfit += profit
            if (cumulativeProfit < 0) {
                cumulativeLost += cumulativeProfit
                cumulativeProfit = 0
                start = i + 1
            }
        }

        if (-cumulativeLost > cumulativeProfit) return -1

        return start
    }
}

fun main() {
    val s = SolutionGST()
    assertEquals(s.canCompleteCircuit(intArrayOf(1,2,3,4,5), intArrayOf(3,4,5,1,2)), 3)
    assertEquals(s.canCompleteCircuit(intArrayOf(2,3,4), intArrayOf(3,4,3)), -1)
    assertEquals(s.canCompleteCircuit(intArrayOf(7,1,0,11,4), intArrayOf(5,9,1,2,5)), 3)
    assertEquals(s.canCompleteCircuit(intArrayOf(3,3,4), intArrayOf(3,4,4)), -1)
    assertEquals(s.canCompleteCircuit(intArrayOf(5,1,2,3,4), intArrayOf(4,4,1,5,1)),4 )
}