package leetcode

class Solution17 {
    private val map = mapOf(
        '2' to "abc",
        '3' to "def",
        '4' to "ghi",
        '5' to "jkl",
        '6' to "mno",
        '7' to "pqrs",
        '8' to "tuv",
        '9' to "wxyz"
    )

    fun letterCombinations(digits: String): List<String> {
        if (digits.isEmpty()) return emptyList()
        return recur(digits, listOf(""))
    }

    private fun recur(digits: String, inProgress: List<String>): List<String> {
        if (digits.isEmpty()) {
            return inProgress
        }

        val result = mutableListOf<String>()
        map[digits[0]]!!.forEach { ch ->
            val newInProgress = inProgress.map { it + ch }
            result.addAll(recur(digits.substring(1), newInProgress))
        }
        return result
    }
}

fun main() {
    val s = Solution17()
    println(s.letterCombinations(""))
}
