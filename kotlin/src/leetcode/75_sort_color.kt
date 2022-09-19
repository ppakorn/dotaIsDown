import kotlin.test.assertEquals

class Solution75 {
    fun sortColors(nums: IntArray): Unit {
        var start = nums.size - 1
        for (i in nums.indices) {
            if (nums[i] != 0) {
                start = i
                break
            }
        }

        var end = 0
        for (i in nums.indices.reversed()) {
            if (nums[i] != 2) {
                end = i
                break
            }
        }

        loop@ for (i in start..end) {
            if (i > end) break
            when(nums[i]) {
                0 -> {
                    swap(nums, i, start)
                    start += 1
                }
                1 -> continue@loop
                2 -> {
                    swap(nums, i, end)
                    end = newEnd(nums, end)
                    if (nums[i] == 0) {
                        swap(nums, i, start)
                        start += 1
                    }
                }
            }
        }
    }

    private fun swap(nums: IntArray, i: Int, j: Int) {
        val temp = nums[i]
        nums[i] = nums[j]
        nums[j] = temp
    }

    private fun newEnd(nums: IntArray, end: Int): Int {
        var newEnd = end - 1
        while (newEnd >= 0) {
            if (nums[newEnd] != 2) {
                return newEnd
            }
            newEnd -= 1
        }
        return newEnd
    }
}

fun main() {
    val s = Solution75()
//    val c1 = intArrayOf(0, 0 ,0 ,0)
//    s.sortColors(c1)
//    assertEquals(intArrayOf(0, 0 ,0 ,0).contentToString(), c1.contentToString())
//    val c2 = intArrayOf(1, 1, 1, 1, 1)
//    s.sortColors(c2)
//    assertEquals(intArrayOf(1, 1, 1, 1, 1).contentToString(), c2.contentToString())
//    val c3 = intArrayOf(2, 2, 2, 2)
//    s.sortColors(c3)
//    assertEquals(intArrayOf(2, 2, 2, 2).contentToString(), c3.contentToString())
//    val c4 = intArrayOf(0, 0, 2, 1, 2, 0, 2, 1, 1, 2, 2)
//    s.sortColors(c4)
//    assertEquals(intArrayOf(0, 0, 0, 1, 1, 1, 2, 2, 2, 2, 2).contentToString(), c4.contentToString())
    val c5 = intArrayOf(2,0,1)
    s.sortColors(c5)
    assertEquals(intArrayOf(0, 1, 2).contentToString(), c5.contentToString())}