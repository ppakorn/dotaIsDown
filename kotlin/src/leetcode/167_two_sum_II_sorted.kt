package leetcode

class Solution167 {
    fun twoSum(numbers: IntArray, target: Int): IntArray {
        var i = 0
        var j = numbers.size - 1

        while (i < j) {
            if (numbers[i] + numbers[j] == target) {
                return intArrayOf(i + 1, j + 1)
            } else if (numbers[i] + numbers[j] < target) {
                i += 1
            } else {
                j -= 1
            }
        }

        // Should never reach here, test case always has 1 solution
        return intArrayOf(0, 0)
    }
}

fun main() {
    val s = Solution167()
    println(s.twoSum(intArrayOf(-1, 0), -1).contentToString())
    println(s.twoSum(intArrayOf(2,7,11,15,80,81,155), 161).contentToString())
    println(s.twoSum(intArrayOf(2, 3, 4), 6).contentToString())
}