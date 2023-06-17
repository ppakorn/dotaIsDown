package leetcode

import kotlin.math.min

class Solution30 {
    // sliding window size = จำนวนคำ * ความยาว (ความยาวเท่ากันเสมอ)
    // slide หนึ่งทีคือ ลบคำซ้ายสุดออกจาก map ก่อนหน้า เพิ่มคำหลังสุด(ที่ได้ตัวหลังมา) ลง map
    // map มีจำนวนเท่ากับความยาว word เหมือนเป็นจุดเริ่มต้นของ window ที่จะนับคำ
    fun findSubstring(s: String, words: Array<String>): List<Int> {
        val wordLength = words[0].length
        val idealMap = mutableMapOf<String, Int>()
        words.forEach {
            idealMap[it] = (idealMap[it] ?: 0) + 1
        }

        val wordSet = words.toSet()
        val mem = Array(wordLength) { mutableMapOf<String, Int>() }
        val result = mutableListOf<Int>()

        for (i in 0 until (words.size - 1) * wordLength + 1) {
            val limit = min(i + wordLength, s.length)
            val sub = s.substring(i until limit)
            if (wordSet.contains(sub)) {
                mem[i % wordLength][sub] = (mem[i % wordLength][sub] ?: 0) + 1
            }
        }
        if (mem[0] == idealMap) {
            result.add(0)
        }

        val windowSize = words.size * wordLength
        for (i in 1 until s.length - windowSize + 1) {
            val previousMod = (i - 1) % wordLength
            val previousWord = s.substring(i - 1 until i - 1 + wordLength)
            mem[previousMod][previousWord] = (mem[previousMod][previousWord] ?: 0) - 1
            if (mem[previousMod][previousWord]!! <= 0) {
                mem[previousMod].remove(previousWord)
            }

            val mod = i % wordLength
            val word = s.substring(i + windowSize - wordLength until i + windowSize)
            if (wordSet.contains(word)) {
                mem[i % wordLength][word] = (mem[i % wordLength][word] ?: 0) + 1
                if (mem[mod] == idealMap) {
                    result.add(i)
                }
            }
        }

        return result
    }
}

fun main() {
    val s = Solution30()
    println(s.findSubstring("lingmindraboofooowingdingbarrwingmonkeypoundcake", arrayOf("fooo","barr","wing","ding","wing")))
}