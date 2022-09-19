import kotlin.test.assertEquals

class Solution121 {
    fun maxProfit(prices: IntArray): Int {
        var maxSell = 0
        var minBuy = Int.MAX_VALUE
        for (p in prices) {
            minBuy = minOf(minBuy, p)
            maxSell = maxOf(maxSell, p - minBuy)
        }
        return maxSell
    }
}

fun main() {
    val s = Solution121()
    assertEquals(s.maxProfit(intArrayOf(7,1,5,3,6,4)), 5)
    assertEquals(s.maxProfit(intArrayOf(7,6,5,3)), 0)
    assertEquals(s.maxProfit(intArrayOf()), 0)
    assertEquals(s.maxProfit(intArrayOf(7)), 0)
    assertEquals(s.maxProfit(intArrayOf(7,1)), 0)
    assertEquals(s.maxProfit(intArrayOf(2,9)), 7)
}