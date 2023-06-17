package leetcode

import java.util.PriorityQueue
import kotlin.math.min

class KthLargest(val k: Int, nums: IntArray) {
    private val size = min(k, nums.size)
//    val list = nums.sortedDescending().slice(0 until size).toMutableList()
    private val heap = PriorityQueue(nums.sortedDescending().slice(0 until size))

    fun add(`val`: Int): Int {
        if (heap.size < k) {
            heap.add(`val`)
        } else if (`val` > heap.peek()) {
            heap.poll()
            heap.add(`val`)
        }

        return heap.peek()
    }


//    fun add1(`val`: Int): Int {
//        if (list.size < k || `val` > list[k - 1]) {
//            insert(`val`)
//        }
//        return list[k - 1]
//    }
//
//
//    fun insert(v: Int) {
//        if (list.size == k) {
//            list.removeAt(k - 1)
//        }
//
//        var i = 0
//        while (i < list.size) {
//            if (v >= list[i]) {
//                list.add(i, v)
//                break
//            }
//            i += 1
//        }
//
//        if (i == list.size) {
//            list.add(v)
//        }
//    }
}

fun main() {
    val kthLargest = KthLargest(2, listOf(0).toIntArray())
    println(kthLargest.add(-1))
    println(kthLargest.add(1))
    println(kthLargest.add(-2))
    println(kthLargest.add(-4))
    println(kthLargest.add(3))
}