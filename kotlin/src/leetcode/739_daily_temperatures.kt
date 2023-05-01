import kotlin.test.assertEquals

class Solution739 {
    fun dailyTemperatures(T: IntArray): IntArray {
        if (T.size == 1) return intArrayOf(0)
        val stack = mutableListOf(0)
        val result = IntArray(T.size)
        for (i in 1 until T.size) {
            val temp = T[i]
            while (stack.isNotEmpty() && T[stack.last()] < temp) {
                val index = stack.removeAt(stack.lastIndex)
                result[index] = i - index
            }
            stack.add(i)
        }
        return result
    }
}

fun main() {
    val s = Solution739()
    assertEquals(s.dailyTemperatures(intArrayOf(73, 74, 75, 71, 69, 72, 76, 73)).contentToString(), intArrayOf(1, 1, 4, 2, 1, 1, 0, 0).contentToString())
    assertEquals(s.dailyTemperatures(intArrayOf(73)).contentToString(), intArrayOf(0).contentToString())
    assertEquals(s.dailyTemperatures(intArrayOf(80, 74, 75, 71, 69, 72, 76, 73)).contentToString(), intArrayOf(0, 1, 4, 2, 1, 1, 0, 0).contentToString())
    assertEquals(s.dailyTemperatures(intArrayOf(80, 74, 74, 75, 71, 69, 72, 76, 73)).contentToString(), intArrayOf(0, 2, 1, 4, 2, 1, 1, 0, 0).contentToString())
}