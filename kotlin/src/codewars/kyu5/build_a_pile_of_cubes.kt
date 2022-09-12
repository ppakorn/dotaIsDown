package codewars.kyu5

import kotlin.test.assertEquals

object ASum {

    private fun sumCube(n: Long): Long {
        return if (n % 2 == 0L) {
            n * n / 4 * (n + 1) * (n + 1)
        } else {
            (n + 1) * (n + 1) / 4 * n * n
        }
    }

    fun findNb(m: Long): Long {
        var r = 77935L
        var l = 0L
        while (l <= r) {
            val mid = (l + r) / 2
            val sum = sumCube(mid)
            if (sum == m) {
                return mid
            }

            if (sum > m) {
                r = mid - 1
            } else {
                l = mid + 1
            }
        }
        return -1
    }
}

fun main() {
//    var sum = 0L
//    var i = 1F
//    while (sum >= 0) {
//        sum += i.pow(3).toLong()
//        println("$sum,")
//        i += 1
//    }
//    println(i)

//    println(ASum.sumCube(77935))
    assertEquals(ASum.findNb(56396345062501), -1L)
    assertEquals(ASum.findNb(6132680780625), 2225L)
    assertEquals(ASum.findNb(28080884739601), -1L)
    assertEquals(ASum.findNb(1071225), 45L)
}