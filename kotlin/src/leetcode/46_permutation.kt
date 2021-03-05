class Solution46 {
    fun permute(nums: IntArray): List<List<Int>> {
        val result = mutableListOf<List<Int>>()
        permute(listOf(), nums.toList(), result)
        return result
    }

    private fun permute(current: List<Int>, remain: List<Int>, result: MutableList<List<Int>>) {
        if (remain.isEmpty()) {
            result.add(current)
            return
        }

        for (i in remain) {
            permute(current + i, remain - i, result)
        }
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