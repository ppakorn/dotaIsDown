package leetcode

class Solution168_2 {
    fun convertToTitle(columnNumber: Int): String {
        var n = columnNumber
        var result = ""

        while (n > 0) {
            result += 'A' + (n % 26) - 1
            n = (n - 1) / 26
        }

        return result.reversed().replace('@', 'Z')
    }
}

fun main() {
    val s = Solution168_2()
//    println(s.convertToTitle(1))
//    println(s.convertToTitle(25))
//    println(s.convertToTitle(26))
//    println(s.convertToTitle(27))
//    println(s.convertToTitle(52))
    println(s.convertToTitle(676))
    println(s.convertToTitle(675))
    println(s.convertToTitle(677))
}