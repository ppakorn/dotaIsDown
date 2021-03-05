class Solution38 {
    fun countAndSay(n: Int): String {
        var c = listOf(1)
        (1 until n).forEach { _ ->
            c = countAndSay(c)
        }
        return c.joinToString("")
    }

    private fun countAndSay(c: List<Int>): List<Int> {
        val newC = mutableListOf<Int>()
        var previous = c[0]
        var count = 1
        var i = 1
        while (i < c.size) {
            val current = c[i]
            if (current == previous) {
                count += 1
            } else {
                newC.add(count)
                newC.add(previous)
                previous = current
                count = 1
            }
            i += 1
        }
        newC.add(count)
        newC.add(previous)
        return newC
    }
}

fun main() {
    val s = Solution38()
    println(s.countAndSay(1))
    println(s.countAndSay(2))
    println(s.countAndSay(3))
    println(s.countAndSay(4))
    println(s.countAndSay(5))
}