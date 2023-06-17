package leetcode

import java.util.LinkedList
import java.util.Queue
import kotlin.math.max
import kotlin.math.min

class Solution135 {
    fun candy(ratings: IntArray): Int {
        if (ratings.size == 1) return 1

        val candies = IntArray(ratings.size) { 0 }
        val queue: Queue<Int> = LinkedList()
        var count = 0
        ratings.forEachIndexed { i, r ->
            val lowest = when (i) {
                0 -> r <= ratings[1]
                ratings.size - 1 -> r <= ratings[i - 1]
                else -> r <= ratings[i - 1] && r <= ratings[i + 1]
            }
            if (lowest) {
                queue.add(i)
            }
        }

        while (queue.isNotEmpty()) {
            val i = queue.remove()
            if (candies[i] > 0) {
                continue
            }

            val leftCandy: Int
            val leftRating: Int
            val rightCandy: Int
            val rightRating: Int

            when (i) {
                0 -> {
                    leftCandy = 0
                    leftRating = Int.MAX_VALUE
                    rightCandy = candies[1]
                    rightRating = ratings[1]
                }
                ratings.size - 1 -> {
                    leftCandy = candies[i - 1]
                    leftRating = ratings[i - 1]
                    rightCandy = 0
                    rightRating = Int.MAX_VALUE
                }
                else -> {
                    leftCandy = candies[i - 1]
                    leftRating = ratings[i - 1]
                    rightCandy = candies[i + 1]
                    rightRating = ratings[i + 1]
                }
            }

            val leftOk = leftRating >= ratings[i] || leftCandy > 0
            val rightOk = rightRating >= ratings[i] || rightCandy > 0
            if (leftOk && rightOk) {
                val c = if (leftCandy == 0 && rightCandy == 0) {
                    1
                } else if (leftCandy == 0) {
                    if (ratings[i] == rightRating) {
                        rightCandy
                    } else {
                        rightCandy + 1
                    }
                } else if (rightCandy == 0) {
                    if (ratings[i] == leftRating) {
                        leftCandy
                    } else {
                        leftCandy + 1
                    }
                } else {
                    if (ratings[i] > leftRating && ratings[i] > rightRating) {
                        max(leftCandy, rightCandy) + 1
                    } else if (ratings[i] > rightRating) {
                        rightCandy + 1
                    } else if (ratings[i] > leftRating) {
                        leftCandy + 1
                    } else {
                        min(leftCandy, rightCandy)
                    }
                }
                candies[i] = c
                count += c

                if (i > 0 && leftCandy == 0) {
                    queue.add(i - 1)
                }
                if (i < ratings.size - 1 && rightCandy == 0) {
                    queue.add(i + 1)
                }
            }
        }
        return count
    }
}

fun main() {
    val s = Solution135()
    println(s.candy(intArrayOf(0)))
}