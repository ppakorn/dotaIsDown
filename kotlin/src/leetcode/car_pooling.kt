class SolutionCPL {
    fun carPooling(trips: Array<IntArray>, capacity: Int): Boolean {
        val a = trips.flatMap {
            listOf(Pair(it[0], it[1]), Pair(-it[0], it[2]))
        }.sortedWith(compareBy({ it.second }, { it.first }))

        var count = 0
        var max = 0
        a.forEach {
            count += it.first
            max = maxOf(max, count)
        }

        return max <= capacity
    }
}

fun main() {
    val s = SolutionCPL()
//    println(s.carPooling(arrayOf(intArrayOf(2,1,5), intArrayOf(3,3,7)), 4))
//    println(s.carPooling(arrayOf(intArrayOf(2,1,5), intArrayOf(3,3,7)), 5))
//    println(s.carPooling(arrayOf(intArrayOf(2,1,5), intArrayOf(3,5,7)), 3))
//    println(s.carPooling(arrayOf(intArrayOf(3,2,7), intArrayOf(3,7,9), intArrayOf(8,3,9)), 11))
    println(s.carPooling(arrayOf(intArrayOf(4,5,6), intArrayOf(6,4,7), intArrayOf(4,3,5), intArrayOf(2,3,5)), 13))
}