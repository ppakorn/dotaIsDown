class SolutionLTD {
    fun largestTimeFromDigits(A: IntArray): String {
        val l1 = A.toList()
        val hr1s = findPossibleDigits(l1, 2) ?: return ""
        hr1s.distinct().sortedDescending().forEach { hr1 ->
            val l2 = l1 - hr1
            val hr2s = if (hr1 == 2) {
                findPossibleDigits(l2, 3)
            } else {
                findPossibleDigits(l2, 9)
            } ?: return@forEach

            hr2s.distinct().sortedDescending().forEach { hr2 ->
                val l3 = l2 - hr2
                val m1 = findPossibleDigits(l3, 5)?.max() ?: return@forEach
                val m2 = findPossibleDigits(l3 - m1, 9)?.first() ?: return@forEach
                return "$hr1$hr2:$m1$m2"
            }
        }
        return ""
    }

    private fun findPossibleDigits(A: List<Int>, ceiling: Int): List<Int>? {
        val a = A.filter { it <= ceiling }
        return if (a.isEmpty()) {
            null
        } else {
            a
        }
    }
}

fun main() {
    val s = SolutionLTD()
    println(s.largestTimeFromDigits(intArrayOf(1,2,3,4)))
    println(s.largestTimeFromDigits(intArrayOf(4,2,3,4)))
    println(s.largestTimeFromDigits(intArrayOf(3,3,3,4)))
    println(s.largestTimeFromDigits(intArrayOf(1,2,6,6)))
    println(s.largestTimeFromDigits(intArrayOf(2,7,4,3)))
    println(s.largestTimeFromDigits(intArrayOf(2,0,6,6)))

}