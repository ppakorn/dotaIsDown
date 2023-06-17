package leetcode

class Solution6 {
    fun convert(s: String, numRows: Int): String {
        if (numRows == 1) return s
        var direction = 1
        var row = 0
        val z = List(numRows) { mutableListOf<Char>() }

        s.forEach { c ->
            z[row].add(c)

            if (row == 0) {
                direction = 1
            } else if (row == numRows - 1) {
                direction = -1
            }

            row += direction
        }

        return z.joinToString("") { it.joinToString("") }
    }
}

fun main() {
    val s = Solution6()
    println(s.convert("ABCD", 2))
}
