package arise

import kotlin.math.max

class ChooseAscending {
    fun solution(A: IntArray): Int {
        // Sort A ascending
        A.sort()
        val set = A.toSet()

        var max = 0
        // For each difference, check how many times it can go
        // If the difference is 0, count
        for (i in A.indices) {
            for (j in i + 1 until A.size) {
                val dif = A[j] - A[i]
                if (dif == 0) {
                    max = max(max, A.count { it == A[i] })
                } else {
                    var count = 2
                    while (set.contains(A[i] + count * dif)) {
                        count += 1
                    }
                    max = max(max, count)
                }
            }
        }

        return max
    }
}

fun main() {
    val s = ChooseAscending()
    println(s.solution(intArrayOf(1,3,5,4,4,4)))
    println(s.solution(intArrayOf(12,12,12,10,15)))
    println(s.solution(intArrayOf(4,7,1,5,3)))
    println(s.solution(intArrayOf(18,26,18,24,24,20,22)))
}