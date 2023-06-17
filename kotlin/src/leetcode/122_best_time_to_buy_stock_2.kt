package leetcode

class Solution122 {
    fun maxProfitOld(prices: IntArray): Int {
        var buy = prices[0]
        var sell = prices[0]
        var profit = 0

        for (i in 1 until prices.size) {
            if (prices[i] > sell) {
                profit -= sell - buy
                sell = prices[i]
                profit += sell - buy
            } else if (prices[i] < sell) {
                buy = prices[i]
                sell = prices[i]
            }
        }

        return profit
    }

    // มองว่าเก็บทุกขาขึ้น ซื้อขายทุกครั้งที่ขึ้น วันที่ลงไม่สนใจ
    fun maxProfit(prices: IntArray): Int {
        var profit = 0
        for (i in 1 until prices.size) {
            if (prices[i] > prices[i - 1]) {
                profit += prices[i] - prices[i - 1]
            }
        }
        return profit
    }
}

fun main() {
    val s = Solution122()
    println(s.maxProfit(intArrayOf(7,1,5,3,6,4)))
    println(s.maxProfit(intArrayOf(1,2,3,4,5)))
    println(s.maxProfit(intArrayOf(7,6,4,3,1)))
    println(s.maxProfit(intArrayOf(7,1,5,8,3,6,4)))

}