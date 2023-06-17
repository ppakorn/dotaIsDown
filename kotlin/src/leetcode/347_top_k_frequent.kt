package leetcode

class Solution347 {
    fun topKFrequent(nums: IntArray, k: Int): IntArray {
        val count = mutableMapOf<Int, Int>()
        val frequent = mutableMapOf<Int, MutableSet<Int>>()
        nums.forEach {
            if (count[it] == null) {
                count[it] = 0
            }
            frequent[count[it]]?.remove(it)
            count[it] = count[it]!! + 1

            if (frequent[count[it]] == null) {
                frequent[count[it]!!] = mutableSetOf()
            }
            frequent[count[it]!!]!!.add(it)
        }

        val answers = mutableListOf<Int>()
        val keys = frequent.keys.sortedDescending()
        for (i in keys) {
            answers.addAll(frequent[i]!!)
            if (answers.size >= k) {
                break
            }
        }
        return answers.toIntArray()
    }

    fun transformInput(str: String): IntArray {
        return str
            .removePrefix("[")
            .removeSuffix("]")
            .split(",")
            .map { a ->
                a.toInt()
            }
            .toIntArray()
    }
}

fun main() {
    val s = Solution347()
    val input = s.transformInput("[1,1,1,2,2,3]")
    println(s.topKFrequent(input, 2).contentToString())
}