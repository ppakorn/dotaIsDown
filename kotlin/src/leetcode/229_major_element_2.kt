class Solution229 {
    fun majorityElement(nums: IntArray): List<Int> {
        var max1: Int? = null
        var max2: Int? = null
        var count1 = 0
        var count2 = 0
        nums.forEach {
            if (it == max1) {
                count1 += 1
                return@forEach
            }
            if (it == max2) {
                count2 += 1
                return@forEach
            }

            if (count1 == 0) {
                max1 = it
                count1 = 1
                return@forEach
            }

            if (count2 == 0) {
                max2 = it
                count2 = 1
                return@forEach

            }

            count1 -= 1
            count2 -= 1
        }

        val result = mutableListOf<Int>()
        if (max1 != null && nums.count { it == max1 } > nums.size / 3) result.add(max1!!)
        if (max2 != null && nums.count { it == max2 } > nums.size / 3) result.add(max2!!)
        return result
    }
}

fun main() {
    val s = Solution229()
    println(s.majorityElement(intArrayOf(3,2,3)))
    println(s.majorityElement(intArrayOf(1,1,1,3,3,2,2,2)))
    println(s.majorityElement(intArrayOf(1,2,3,1,3,1,2,2)))
    println(s.majorityElement(intArrayOf(1,1,1,4,4,3,2,3,3,2,3,2,5,2,2,3,2,3,3,2)))
}
