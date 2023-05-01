class Solution15_3 {
    fun threeSum(nums: IntArray): List<List<Int>> {
        nums.sort()
        val result = mutableListOf<List<Int>>()

        var i = 0
        while (i < nums.size - 2) {
            val x = nums[i]
            var l = i + 1
            var r = nums.lastIndex

            while (l < r) {
                val y = nums[l]
                val z = nums[r]
                val sum = x + y + z
                when {
                    sum == 0 -> {
                        result.add(listOf(x, y, z))
                        while (nums[l] == y && l < r) { l += 1 }
                        while (nums[r] == z && l < r) { r -= 1 }
                    }
                    sum > 0 -> {
                        while (nums[r] == z && l < r) { r -= 1 }
                    }
                    else -> {
                        while (nums[l] == y && l < r) { l += 1 }
                    }
                }
            }


            i += 1
            while (nums[i] == x && i < nums.size - 2) {
                i += 1
            }
        }

        return result
    }
}

fun main() {
    val s = Solution15_3()
    println(s.threeSum(intArrayOf(-1, 0, 1, 2, -1, -4)))        //
    println(s.threeSum(intArrayOf(-2, 1, -1, 2, -1, 4, -4, -1, 0, -1, 0, 1, 2, -1, -4)))        //
    println(s.threeSum(intArrayOf(-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6)))
    println(s.threeSum(intArrayOf(0, 0 ,0)))        //
}