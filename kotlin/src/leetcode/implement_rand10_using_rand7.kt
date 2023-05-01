import kotlin.random.Random

class SolutionRand10 : SolBase() {
    fun rand10(): Int {
        var i = 1000
        while (i >= 40) {
            i = 7 * (rand7() - 1) + rand7() - 1
        }
        // when while stop, i will be 0 - 39
        return i % 10 + 1
    }
}

open class SolBase {
    fun rand7(): Int {
        return Random.nextInt(1, 8)
    }
}

fun main() {
    val s = SolutionRand10()
    (1..100).forEach {
        println(s.rand10())
    }
}