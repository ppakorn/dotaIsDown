class Solution46 {
    fun permute(nums: IntArray): List<List<Int>> {
        return permute(listOf(), nums.toList())
    }

    private fun permute(current: List<Int>, remain: List<Int>): List<List<Int>> {
        if (remain.isEmpty()) {
            return listOf(current)
        }

        val result = mutableListOf<List<Int>>()
        remain.forEach {
            result.addAll(permute(current + it, remain - it))
        }
        return result
    }
}

fun main() {
    val s = Solution46()
    val m4 = s.permute(intArrayOf(1, 2, 3, 4))
    println(m4.size)
    print(m4)
}

private fun print(listOfList: List<List<Int>>) {
    for (list in listOfList) {
        for (n in list) {
            print("$n ")
        }
        println()
    }
}