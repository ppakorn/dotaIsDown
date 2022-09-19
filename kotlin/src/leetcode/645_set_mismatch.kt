class Solution645 {
    fun findErrorNums(nums: IntArray): IntArray {
        // input is less than 10000
        val constant = 100000
        nums.forEach {
            val i = (it - 1) % constant
            nums[i] += constant
        }

        val result = IntArray(2)
        result[0] = nums.indexOfFirst { it > constant * 2 } + 1
        result[1] = nums.indexOfFirst { it < constant } + 1
        return result
    }
}