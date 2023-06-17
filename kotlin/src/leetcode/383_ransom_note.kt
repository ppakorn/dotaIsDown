package leetcode

class Solution383 {
    fun canConstruct(ransomNote: String, magazine: String): Boolean {
        val noteCount = mutableMapOf<Char, Int>()
        ransomNote.forEach {
            noteCount[it] = (noteCount[it] ?: 0) + 1
        }

        val magazineCount = mutableMapOf<Char, Int>()
        magazine.forEach {
            magazineCount[it] = (magazineCount[it] ?: 0) + 1
        }

        noteCount.forEach {
            if (it.value > (magazineCount[it.key] ?: 0)) {
                return false
            }
        }

        return true
    }
}