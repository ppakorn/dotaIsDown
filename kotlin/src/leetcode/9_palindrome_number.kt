package leetcode

class Solution9 {
    fun isPalindrome(x: Int): Boolean {
        val s = x.toString()
        return isPalindrome(s)
    }

    fun isPalindrome(s: String): Boolean {
        for (i in 0 until s.length / 2) {
            if (s[i] != s[s.length - i - 1]) {
                return false
            }
        }
        return true
    }
}

fun main() {
    val s = Solution9()
    println(s.isPalindrome("abba"))
    println(s.isPalindrome("aba"))
    println(s.isPalindrome("abbb"))
}