package leetcode

class Solution12 {
    private val valToRoman = mapOf(
        1 to "I",
        4 to "IV",
        5 to "V",
        9 to "IX",
        10 to "X",
        40 to "XL",
        50 to "L",
        90 to "XC",
        100 to "C",
        400 to "CD",
        500 to "D",
        900 to "CM",
        1000 to "M"
    )
    private val order = valToRoman.keys.sortedDescending()
    fun intToRoman(num: Int): String {
        var n = num
        var result = ""
        while (n > 0) {
            val i = findLargest(n)
            result += valToRoman[i]!!
            n -= i
        }
        return result
    }

    private fun findLargest(n: Int): Int {
        for (i in order) {
            if (n >= i) {
                return i
            }
        }
        throw Exception("Cannot find largest")
    }
}

fun main() {
    val s = Solution12()
    println(s.intToRoman(3))
    println(s.intToRoman(58))
    println(s.intToRoman(1994))
    println(s.intToRoman(3999))
}