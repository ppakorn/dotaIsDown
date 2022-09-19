class SolutionMPS {
    fun maxProduct(nums: IntArray): Int {
        if (nums.isEmpty()) return 0
        if (nums.size == 1) return nums[0]

        val split = mutableListOf<List<Int>>()
        var group = mutableListOf<Int>()
        nums.forEach {
            if (it == 0) {
                if (group.isNotEmpty()) {
                    split.add(group)
                    group = mutableListOf()
                }
            } else {
                group.add(it)
            }
        }
        if (group.isNotEmpty()) {
            split.add(group)
            group = mutableListOf()
        }

        return split.map { findMax(it) }.max()!!
    }

    private fun findMax(l: List<Int>): Int {
        val minus = l.mapIndexed { i, x ->
            if (x < 0) {
                i
            } else {
                -1
            }
        }.filter { it > -1 }
        return if (minus.size % 2 == 0) {
            l.reduce { acc, i -> acc * i }
        } else {
            val firstMinus = minus.first()
            val lastMinus = minus.last()
            val max1 = try {
                l.slice(0 until lastMinus).reduce { acc, i -> acc * i }
            } catch (e: Exception) {
                0
            }
            val max2 = try {
                l.slice(firstMinus + 1 until l.size).reduce { acc, i -> acc * i }
            } catch (e: Exception) {
                0
            }
            maxOf(max1, max2)
        }
    }
}

fun main() {
    val s = SolutionMPS()
    println(s.maxProduct(intArrayOf(2,3,-2,4)))
    println(s.maxProduct(intArrayOf(-2,0,-1)))
    println(s.maxProduct(intArrayOf(-3,4,-2,2,1,4,5,-1,9)))
    println(s.maxProduct(intArrayOf()))
    println(s.maxProduct(intArrayOf(-2)))
}