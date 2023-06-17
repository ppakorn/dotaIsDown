package leetcode

class Solution1232 {
    fun checkStraightLine(coordinates: Array<IntArray>): Boolean {
        val slope = if (coordinates[1][0] == coordinates[0][0]) {
            null
        } else {
            (coordinates[1][1].toDouble() - coordinates[0][1]) / (coordinates[1][0] - coordinates[0][0])
        }
        for (i in 2 until coordinates.size) {
            val slope2 = if (coordinates[i][0] == coordinates[i - 1][0]) {
                null
            } else {
                (coordinates[i][1].toDouble() - coordinates[i - 1][1]) / (coordinates[i][0] - coordinates[i - 1][0])
            }
            if (slope != slope2) {
                return false
            }
        }
        return true
    }
}