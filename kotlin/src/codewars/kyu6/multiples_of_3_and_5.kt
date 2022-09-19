package codewars.kyu6

class SolutionM35 {
    fun solution(number: Int): Int {
        val n = number - 1
        if (n <= 0) {
            return 0
        }

        val k3 = n / 3
        val k5 = n / 5
        val k15 = n / 15

        return (3 * k3 * (k3 + 1) + 5 * k5 * (k5 + 1) - 15 * k15 * (k15 + 1)) / 2
    }
}

fun main() {
    val s = SolutionM35()
    println(s.solution(10))
    println(s.solution(20))
    println(s.solution(200))
}