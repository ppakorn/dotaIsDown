class Solution5505 {
    fun maxSumRangeQuery(nums: IntArray, requests: Array<IntArray>): Int {
        val a = requests.flatMap {
            listOf(Pair(1, it[0]), Pair(-1, it[1] + 1))
        }.sortedBy { it.second }

        val count = MutableList(nums.size) {0}
        var i = 0
        var c = 0
        a.forEach { pair ->
            (i until pair.second).forEach {
                count[it] = c
            }
            i = pair.second
            c += pair.first
        }

        count.sortDescending()
        val sortedNums = nums.sortedDescending()
        var sum: Long = 0
        count.forEachIndexed { i, c ->
            if (c == 0) return (sum % 1000000007).toInt()
            sum += (sortedNums[i] * c)
        }

        return (sum % 1000000007).toInt()
    }
}

fun main() {
    val s = Solution5505()
    println(s.maxSumRangeQuery(intArrayOf(1,2,3,4,5), arrayOf(intArrayOf(1,3), intArrayOf(0,1))))
    println(s.maxSumRangeQuery(intArrayOf(1,2,3,4,5,6), arrayOf(intArrayOf(0,1))))
    println(s.maxSumRangeQuery(intArrayOf(1,2,3,4,5,10), arrayOf(intArrayOf(0,2),intArrayOf(1,3), intArrayOf(1,1))))

}