package leetcode

import kotlin.math.min

class Solution322v2 {
    private val fewest = mutableMapOf(0 to 0)

    fun coinChange(coins: IntArray, amount: Int): Int {
        coins.sortDescending()
        fewest.clear()
        fewest[0] = 0

        val result = recur(coins, amount)
        return if (result == Int.MAX_VALUE) {
            -1
        } else {
            result
        }
    }

    // fewest(amount) = 1 + min( fewest(amount - c1), fewest(amount - c2), ... )
    private fun recur(descCoin: IntArray, amount: Int): Int {
        if (fewest[amount] != null) {
            return fewest[amount]!!
        }

        if (amount < 0) {
            return Int.MAX_VALUE
        }

        var min = Int.MAX_VALUE
        descCoin.forEach { c ->
            min = min(min, recur(descCoin, amount - c))
        }

        return if (min < Int.MAX_VALUE) {
            fewest[amount] = min + 1
            min + 1
        } else {
            fewest[amount] = Int.MAX_VALUE
            Int.MAX_VALUE
        }
    }
}

fun main() {
    val s = Solution322v2()
    println(s.coinChange(intArrayOf(2,4,6,8,10), 99))
}