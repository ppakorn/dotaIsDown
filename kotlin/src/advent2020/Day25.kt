package advent2020

class Day25 {

    fun execute() {
        val cardPub = 6930903L
        val doorPub = 19716708L
//        val cardPub = 5764801L
//        val doorPub = 17807724L
//        println(findLoopSize(cardPub))
//        println(findLoopSize(doorPub))

        val cardLoopSize = 16190552L
        val doorLoopSize = 11893237L
        println(transform(cardPub, doorLoopSize))
        println(transform(doorPub, cardLoopSize))
    }

    private fun findLoopSize(pub: Long): Long {
        var value = 1L
        var loop = 0L
        val div = 20201227
        while (value != pub) {
            value *= 7
            value %= div
            loop += 1
        }
        return loop
    }

    private fun transform(pub: Long, loopSize: Long): Long {
        var value = 1L
        val div = 20201227
        for (i in 0 until loopSize) {
            value *= pub
            value %= div
        }
        return value
    }
}

fun main() {
    val d = Day25()
    d.execute()
}