package leetcode

class Solution13 {
    private val symbols = mapOf(
        'I' to 1,
        'V' to 5,
        'X' to 10,
        'L' to 50,
        'C' to 100,
        'D' to 500,
        'M' to 1000
    )

    fun romanToInt(s: String): Int {
        var sum = 0
        for (i in s.indices) {
            val value = symbols[s[i]]!!
            if (i < s.length - 1 && value < symbols[s[i + 1]]!!) {
                sum -= value
            } else {
                sum += value
            }
        }
        return sum
    }
}

fun main() {
    val s = Solution13()
    println(s.romanToInt("III"))
    println(s.romanToInt("LVIII"))
    println(s.romanToInt("MCMXCIV"))
}