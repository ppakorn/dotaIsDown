package leetcode

class Solution134 {
    // ถ้าตอน start ที่ 1 เป็น + แล้ววิ่งไปถึง 10 ไม่ได้ 2 ก็ทำไมได้
    // ถ้าไปต่อไม่ได้ก็เริ่มใหม่ เพราะฉะนั้นก็ start เป็น + ตลอด
    // ต้องเก็บ gas ที่ต้องใช้เวลาไม่ผ่านไว้ เผื่อเคส -1
    fun canCompleteCircuit(gas: IntArray, cost: IntArray): Int {
        var currentGas = 0
        var start = 0
        var requireGasToStart = 0
        for (i in cost.indices) {
            currentGas += gas[i]
            currentGas -= cost[i]
            if (currentGas < 0) {
                requireGasToStart += currentGas
                currentGas = 0
                start = i + 1
            }
        }

        return if (currentGas + requireGasToStart >= 0) {
            start
        } else {
            -1
        }
    }
}

fun main() {
    val s = Solution134()
    println(s.canCompleteCircuit(intArrayOf(1,2,3,4,5), intArrayOf(3,4,5,1,2)))
    println(s.canCompleteCircuit(intArrayOf(2,3,4), intArrayOf(3,4,3)))
}