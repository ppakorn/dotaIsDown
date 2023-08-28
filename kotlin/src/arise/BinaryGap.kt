package arise

import kotlin.math.max

fun solution(N: Int): Int {
    val binary = N.toString(2)
    var max = 0
    var count = 0
    binary.forEach {
        if (it == '0') {
            count += 1
        } else {
            max = max(max, count)
            count = 0
        }
    }
    return max
}

fun main() {
    println(solution(1041))
}