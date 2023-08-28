package arise

class UniqueIntArray {
    fun solution(A: IntArray): Int {
        // How many times each int appears in A
        // For example, [5,3,3,2,5,2,3,2] will produce {5 -> 2, 2 -> 3, 3 -> 3}
        val appearances = mutableMapOf<Int, Int>()
        A.forEach {
            appearances[it] = (appearances[it] ?: 0) + 1
        }

        // Time appearance to how many int appear for that time
        // For example, [5,3,3,2,5,2,3,2] will produce {3 -> 2, 2 -> 1}
        // which means there are 2 integers that appear 3 times (2 and 3) and 1 integer appears 2 time (5)
        val times = mutableMapOf<Int, Int>()
        appearances.values.forEach { t ->
            times[t] = (times[t] ?: 0) + 1
        }

        var deleteCount = 0
        val immutableTimes = times.toMap()
        immutableTimes.forEach { (time, count) ->
            if (count == 1) {
                return@forEach
            }

            // For each duplicate, delete until unique or zero
            for (i in 1 until count) {
                var j = 1
                while (time - j > 0 && times[time - j] != null) {
                    j += 1
                }
                if (time - j > 0) {
                    times[time - j] = 1
                }
                deleteCount += j
            }

            // Unnecessary, but useful for debugging
            times[time] = 1
        }

        return deleteCount
    }
}

fun main() {
    val s = UniqueIntArray()
    println(s.solution(intArrayOf(1,1,1,2,2,2)))
    println(s.solution(intArrayOf(5,3,3,2,5,2,3,2)))
    println(s.solution(intArrayOf(1,1,2,3,4,5)))
    println(s.solution(intArrayOf(1,2,3,4,5)))
    println(s.solution(intArrayOf(100000,100000,5,5,5,2,2,2,0,0)))
}
