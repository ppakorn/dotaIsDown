package leetcode

import kotlin.math.max

class Solution1140 {
    fun stoneGameII(piles: IntArray): Int {
        val diff = dp(piles, 0, 1)
        val sum = piles.sum()
        return (sum + diff) / 2
    }

    private val mem = mutableMapOf<Int, MutableMap<Int, Int>>()

    // วิธีคิด
    // dp คือ return max difference โดยเอาคนเล่นตานั้นเป็นตัวตั้ง + คือกำไร - คือขาดทุน
    // ถ้าคนเล่นตานั้นเลือก i ตาถัดไปอีกฝั่งก็จะได้ dp(i + 1, m) recursive ลงไปเรื่อยๆ
    // เพราะว่า alice เล่นก่อน root เลยเป็น max difference alice - bob
    // ถ้าเป็น + alice ชนะ เป็น - bob ชนะ
    // แล้วค่อยมาคำนวณหาแต้มที่ได้จาก difference อีกที

    private fun dp(piles: IntArray, i: Int, m: Int): Int {
        if (mem[i]?.get(m) != null) return mem[i]!![m]!!

        if (piles.size - i <= 2 * m) {
            return piles.slice(i until piles.size).sum()
        }

        var max = Int.MIN_VALUE
        for (x in 1..2 * m) {
            val playerValue = piles.slice(i until i + x).sum()
            val newM = max(x, m)
            max = max(max, playerValue - dp(piles, i + x, newM))
        }

        if (mem[i] == null) {
            mem[i] = mutableMapOf()
        }
        mem[i]!![m] = max
        return max
    }
}

fun main() {
    val s = Solution1140()
    println(s.stoneGameII(listOf(2,7,9,4,4).toIntArray()))
    println(s.stoneGameII(listOf(1,2,3,4,5,100).toIntArray()))
}