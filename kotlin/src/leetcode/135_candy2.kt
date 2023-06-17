package leetcode

import kotlin.math.max
import kotlin.math.min

class Solution135SecondTime {
    // วิ่ง O(n) สองครั้ง ซ้ายไปขวาคือทำให้ higher จากซ้ายไปขวา
    // จากขวามาซ้าย ทำให้ higher จากขวามาซ้าย
    // O(n) space
    fun candyUsingSpace(ratings: IntArray): Int {
        val candy = IntArray(ratings.size) { 1 }

        for (i in 1 until ratings.size) {
            if (ratings[i] > ratings[i - 1]) {
                candy[i] = candy[i - 1] + 1
            }
        }

        for (i in ratings.size - 2 downTo 0) {
            if (ratings[i] > ratings[i + 1]) {
                if (candy[i] == 1) {
                    candy[i] = candy[i + 1] + 1
                } else {
                    candy[i] = max(candy[i], candy[i + 1] + 1)
                }
            }
        }

        return candy.sum()
    }

    // Peak and Valley
    // มองว่าถ้าเจอทางขึ้นเขา ก็จ่ายไปเรื่อยๆ ตามทาง
    // ตอนลงเขาก็จ่ายไปเรื่อยๆ ตามทาง
    // สมมติทางลง rating เป็น 10, 5, 2 จะจ่าย 1, 2, 3 หรือ 3, 2, 1 ก็เท่ากัน ไม่สนว่าจ่ายให้ใคร
    // peak จะโดนคิดสองรอบทั้่งขาขึ้นขาลง ต้องหักออก
    // ถ้าเจอ = reset ได้
    fun candy(ratings: IntArray): Int {
        // เริ่มแรกแจกคนละเม็ด
        var candy = ratings.size

        var peak = 0
        var valley = 0
        var i = 1
        var current = ""
        while (i < ratings.size) {
            if (ratings[i] > ratings[i - 1]) {
                // this is an up-slope to peak

                if (current == "down") {
                    // end of slope down
                    // peak โดนคิดไปสองครั้งต้องลบออก
                    candy -= min(peak, valley)
                    valley = 0
                    peak = 0
                }

                current = "up"
                peak += 1
                candy += peak

            } else if (ratings[i] < ratings[i - 1]) {
                // this is a down-slope toward a valley
                current = "down"
                valley += 1
                candy += valley
            } else {
                if (current == "down") {
                    // end of slope down
                    // peak โดนคิดไปสองครั้งต้องลบออก
                    candy -= min(peak, valley)
                }
                peak = 0
                valley = 0
                current = ""
            }

            i += 1
        }

        if (current == "down") {
            // end of slope down
            // peak โดนคิดไปสองครั้งต้องลบออก
            candy -= min(peak, valley)
        }

        return candy
    }
}

fun main() {
    val s = Solution135SecondTime()
    println(s.candy(intArrayOf(1,0,2)))
    println(s.candy(intArrayOf(0)))
    println(s.candy(intArrayOf(1,2,2)))
    println(s.candy(intArrayOf(1,3,2,2,1)))
    println(s.candy(intArrayOf(29,51,87,87,72,12)))
    println(s.candy(intArrayOf(1,3,4,5,2)))
}