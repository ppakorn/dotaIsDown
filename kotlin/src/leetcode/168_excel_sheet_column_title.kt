class Solution168 {
    fun convertToTitle(n: Int): String {
        var result = ""
        var nn = n
        while (nn > 0) {
            val q = nn / 26
            val r = nn - 26 * q
            nn = q
            if (r == 0) {
                nn -= 1
                result += 'Z'
            } else {
                result += ('A' + r - 1)
            }
        }

        return result.reversed()
    }
}

fun main() {
    val s = Solution168()
    println(s.convertToTitle(1))
    println(s.convertToTitle(2))
    println(s.convertToTitle(701))
    println(s.convertToTitle(52))
}