package leetcode

class Solution15_4 {
    // two sum from Solution 167
    // วิ่งซ้ายขวา ถ้าเยอะไปตบขวาเข้าให้น้อยลง ถ้าน้อยไปขยับซ้ายให้มากขึ้น
    fun twoSum(numbers: List<Int>, target: Int): List<List<Int>> {
        var i = 0
        var j = numbers.size - 1
        val result = mutableListOf<List<Int>>()

        while (i < j) {
            if (numbers[i] + numbers[j] == target) {
                result.add(listOf(numbers[i], numbers[j]))
                // ขยับ i, j ให้ไม่ซ้ำ
                val valueI = numbers[i]
                val valueJ = numbers[j]
                while (i < j && numbers[i] == valueI) {
                    i += 1
                }
                while (i < j && numbers[j] == valueJ) {
                    j -= 1
                }
            } else if (numbers[i] + numbers[j] < target) {
                i += 1
            } else {
                j -= 1
            }
        }
        return result
    }

    // O(n^2) วิ่งไล่ i ไป
    // แล้วเช็คด้วย two sum ว่ามีผลบวกที่ต้องการมั้ย
    fun threeSum(nums: IntArray): List<List<Int>> {
        nums.sort()
        var i = 0
        val result = mutableListOf<List<Int>>()

        while (i < nums.size - 2) {
            val subList = nums.slice(i + 1 until nums.size)
            val subResults = twoSum(subList, 0 - nums[i])
            for (r in subResults) {
                result.add(listOf(nums[i], r[0], r[1]))
            }

            // ขยับ i ให้ไม่ซ้ำ
            val valueI = nums[i]
            while (i < nums.size - 2 && nums[i] == valueI) {
                i += 1
            }
        }

        return result
    }
}

fun main() {
    val s = Solution15_4()
    println(s.threeSum(intArrayOf(-1,0,1,2,-1,-4)))
}