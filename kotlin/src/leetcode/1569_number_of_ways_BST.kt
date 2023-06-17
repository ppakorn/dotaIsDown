package leetcode

import TreeNode
import java.math.BigInteger
import kotlin.math.max

class Solution1569 {
    private val mod = 1_000_000_000 + 7

    // idea คือ
    // ที่ node parent จะเท่ากับ L * R * C(len(L)+len(R), len(L)) [C คือ เลือก]
    // C(len(L)+len(R), len(L)) คือเอาฝั่ง L กับ R มาเรียงให้ order สองฝั่งไม่เปลี่ยน
    // สมมติ [1,2] [4,5] -> 1,2,4,5 / 1,4,2,5 / 4,1,2,5 / ...
    // L กับ R คือหลังจากที่เรียงไปแล้ว อาจจะมีการสลับได้ เช่น 2,1,3 / 2,3,1 ได้ BTS เดียวกัน
    // - 1 ตอนจบคืออันที่เป็นโจทย์
    // ลำบากแค่ตรง overflow แหละ เพราะต้องคำนวณ factorial
    fun numOfWays(nums: IntArray): Int {
        val root = createBTS(nums)
        val result = calculateWays(root)
        return (result.second - 1).toInt()
    }

    // Return (count, permutation possibility)
    private fun calculateWays(node: TreeNode?): Pair<Int, Long> {
        if (node == null) {
            return Pair(0, 1)
        }
        val left = calculateWays(node.left)
        val right = calculateWays(node.right)
        var result = duplicatePermutation(left.first, right.first)
        result *= left.second
        result %= mod
        result *= right.second
        result %= mod
        return Pair(left.first + right.first + 1, result)
    }

    private fun createBTS(nums: IntArray): TreeNode {
        // Return root
        val root = TreeNode(nums[0])
        for (i in 1 until nums.size) {
            insertNode(root, nums[i])
        }
        return root
    }

    private fun insertNode(currentNode: TreeNode, v: Int) {
        if (v > currentNode.`val`) {
            if (currentNode.right == null) {
                currentNode.right = TreeNode(v)
            } else {
                insertNode(currentNode.right!!, v)
            }
        } else {
            if (currentNode.left == null) {
                currentNode.left = TreeNode(v)
            } else {
                insertNode(currentNode.left!!, v)
            }
        }
    }

    fun duplicatePermutation(a: Int, b: Int): Long {
        // return (a + b)! / a! b! mod
        val max = max(a, b)
        val min = a + b - max
        var result = BigInteger.ONE

        // เอาตัว max หาร suppose a > b
        // ข้างบนจะเหลือ (a + b) * (a + b - 1) * ... * (a + 1) [b ตัว]
        // ข้างล่าง 1 * ... * b
        var divider = 2
        for (i in (max + 1)..(a + b)) {
            result *= i.toBigInteger()
            // พยายามหารเยอะ กัน overflow
            while (divider <= min && result % divider.toBigInteger() == BigInteger.ZERO) {
                result /= divider.toBigInteger()
                divider += 1
            }
        }

        return (result % mod.toBigInteger()).toLong()
    }
}

fun main() {
    val s = Solution1569()
//    println(s.duplicatePermutation(46, 48))
//    println(Long.MAX_VALUE)
    println(s.numOfWays(intArrayOf(19,3,57,34,15,89,58,35,2,33,46,13,40,79,60,30,61,26,54,22,84,51,75,6,87,44,55,48,27,8,72,47,16,69,36,76,41,1,80,62,73,24,93,50,92,65,39,5,32,67,12,29,90,45,9,38,88,52,10,85,74,66,83,18,20,77,49,28,23,53,86,64,78,82,37,42,56,17,81,4,14,70,59,31,7,25,43,68,91,71,21,63,94,11)))
}