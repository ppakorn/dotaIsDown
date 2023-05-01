class Solution322 {
    fun coinChange(coins: IntArray, amount: Int): Int {
        val a = IntArray(amount + 1) {amount + 1}
       a[0] = 0

        for (i in 1..amount) {
            for (c in coins) {
                if (i < c) continue
                a[i] = minOf(a[i], a[i - c] + 1)
            }
        }

        return if (a[amount] == amount + 1) {
            -1
        } else {
            a[amount]
        }
    }
}

fun main() {
    val s = Solution322()
    println(s.coinChange(intArrayOf(1, 2, 5), 11))
    println(s.coinChange(intArrayOf(8, 2, 4), 11))
    println(s.coinChange(intArrayOf(2, 3, 7), 8))
    println(s.coinChange(intArrayOf(2, 3, 6), 125))
    println(s.coinChange(intArrayOf(186, 419, 83, 408), 6249))
}