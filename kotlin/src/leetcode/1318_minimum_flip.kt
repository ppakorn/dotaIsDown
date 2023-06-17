package leetcode

import util.toBinary

class Solution1318 {
    fun minFlips(a: Int, b: Int, c: Int): Int {
        val aBinary = a.toBinary(30)
        val bBinary = b.toBinary(30)
        val cBinary = c.toBinary(30)

        var count = 0
        cBinary.forEachIndexed { i, char ->
            if (char == '0') {
                if (aBinary[i] == '1') {
                    count += 1
                }
                if (bBinary[i] == '1') {
                    count += 1
                }
            } else {
                if (aBinary[i] == '0' && bBinary[i] == '0') {
                    count += 1
                }
            }
        }
        return count
    }
}

fun main() {
    val s = Solution1318()
    println(s.minFlips(5, 2,8 ))
}