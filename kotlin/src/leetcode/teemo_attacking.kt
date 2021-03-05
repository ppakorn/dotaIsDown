class SolutionTMA {
    fun findPoisonedDuration(timeSeries: IntArray, duration: Int): Int {
        if (timeSeries.isEmpty()) return 0

        var count = 0
        var last = timeSeries[0]
        var timeout = last + duration
        timeSeries.forEach {
            count += if (it > timeout) {
                duration
            } else {
                it - last
            }
            last = it
            timeout = it + duration
        }

        count += duration

        return count
    }
}

fun main() {
    val s = SolutionTMA()
    println(s.findPoisonedDuration(intArrayOf(1, 4), 2))
    println(s.findPoisonedDuration(intArrayOf(1, 2), 2))
}