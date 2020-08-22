class Solution406_2 {
    fun reconstructQueue(people: Array<IntArray>): Array<IntArray> {
        val cmp = compareBy<IntArray> { it[0] }.thenByDescending { it[1] }
        val sortedPeople = people.sortedArrayWith(cmp)

        val bit = BinaryIndexTree(people.size + 1)
        for (i in 2 until bit.size) {
            bit.update(i, 1)
        }

        for (i in 1 until bit.size) {
            println("$i = ${bit.getSum(i)}")
        }

        val dummyIntArray = intArrayOf()
        val result = Array(people.size) { dummyIntArray }
        sortedPeople.forEach { q ->
            var l = 1
            var r = bit.size - 1
            while (l < r) {
                val mid = l + (r - l + 1) / 2
                val midSum = bit.getSum(mid)
                when {
                    midSum > q[1] -> { r = mid - 1 }
                    else -> { l = mid }
                }
            }
            result[l - 1] = q
            bit.update(l + 1, -1)
        }
        return result
    }
}

fun main() {
    val s = Solution406_2()
    val case1 = s.reconstructQueue(arrayOf(
        intArrayOf(7, 0),
        intArrayOf(4, 4),
        intArrayOf(5, 0),
        intArrayOf(6, 1),
        intArrayOf(7, 1),
        intArrayOf(5, 2)
    ))
    case1.forEach {
        print(it.contentToString())
        print(", ")
    }
    println()
}