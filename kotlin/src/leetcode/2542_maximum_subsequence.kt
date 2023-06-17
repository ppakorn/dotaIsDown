package leetcode

import java.util.PriorityQueue
import kotlin.math.max

class Solution2542 {
    fun maxScore(nums1: IntArray, nums2: IntArray, k: Int): Long {
        val n = nums1.mapIndexed { index, i -> Pair(i, nums2[index]) }.sortedWith(
            compareByDescending<Pair<Int, Int>> { it.second }
                .thenByDescending { it.first }
        )
        var sum = 0L
        var multiplier = n[k - 1]
        val heapN1 = PriorityQueue<Int>()

        n.subList(0, k).forEach { pair ->
            sum += pair.first
            heapN1.add(pair.first)
        }

        var max = sum * multiplier.second
        n.subList(k, n.size).forEach { new ->
            sum += new.first
            heapN1.add(new.first)
            sum -= heapN1.poll()
            multiplier = new
            max = max(max, sum * multiplier.second)
        }

        return max
    }
}

fun main() {
    val s = Solution2542()
    val nums1 = listOf(4,2,3,1,1).toIntArray()
    val nums2 = listOf(7,5,10,9,6).toIntArray()
    val k = 1
    println(s.maxScore(nums1, nums2, k))
}