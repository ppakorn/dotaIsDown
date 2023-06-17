package leetcode

class Solution73 {
    // ใช้ row แรกเก็บว่าต้องมี column ไหนเป็น 0 บ้าง
    fun setZeroes(matrix: Array<IntArray>): Unit {
        var shouldRow0All0 = false
        matrix[0].forEach {
            if (it == 0) {
                shouldRow0All0 = true
            }
        }

        for (row in 1 until matrix.size) {
            var found0 = false
            for (j in 0 until matrix[row].size) {
                if (matrix[row][j] == 0) {
                    matrix[0][j] = 0
                    found0 = true
                }
            }

            if (found0) {
                for (column in 0 until matrix[row].size) {
                    matrix[row][column] = 0
                }
            }
        }

        for (column in 0 until matrix[0].size) {
            if (matrix[0][column] == 0) {
                for (row in 1 until matrix.size) {
                    matrix[row][column] = 0
                }
            } else if (shouldRow0All0) {
                matrix[0][column] = 0
            }
        }
    }
}