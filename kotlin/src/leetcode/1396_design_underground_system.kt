package leetcode

class Solution1396 {
    private val averageTimes = mutableMapOf<String, Pair<Double, Int>>()
    private val memId = mutableMapOf<Int, Pair<String, Int>>()

    fun checkIn(id: Int, stationName: String, t: Int) {
        memId[id] = Pair(stationName, t)
    }

    fun checkOut(id: Int, stationName: String, t: Int) {
        val checkIn = memId[id]!!
        val key = "${checkIn.first}-$stationName"

        val oldAvgTime = averageTimes[key]?.first ?: 0.0
        val oldCount = averageTimes[key]?.second ?: 0
        val newAvgTime = (oldAvgTime * oldCount + (t - checkIn.second)) / (oldCount + 1)
        averageTimes[key] = Pair(newAvgTime, oldCount + 1)
    }

    fun getAverageTime(startStation: String, endStation: String): Double {
        val key = "$startStation-$endStation"
        return averageTimes[key]!!.first
    }
}

fun main() {
    val undergroundSystem = Solution1396()
    undergroundSystem.checkIn(45, "Leyton", 3)
    undergroundSystem.checkIn(32, "Paradise", 8)
    undergroundSystem.checkIn(27, "Leyton", 10)
    undergroundSystem.checkOut(45, "Waterloo", 15) // Customer 45 "Leyton" -> "Waterloo" in 15-3 = 12

    undergroundSystem.checkOut(27, "Waterloo", 20) // Customer 27 "Leyton" -> "Waterloo" in 20-10 = 10

    undergroundSystem.checkOut(32, "Cambridge", 22) // Customer 32 "Paradise" -> "Cambridge" in 22-8 = 14

    undergroundSystem.getAverageTime(
        "Paradise",
        "Cambridge"
    ) // return 14.00000. One trip "Paradise" -> "Cambridge", (14) / 1 = 14

    undergroundSystem.getAverageTime(
        "Leyton",
        "Waterloo"
    ) // return 11.00000. Two trips "Leyton" -> "Waterloo", (10 + 12) / 2 = 11

    undergroundSystem.checkIn(10, "Leyton", 24)
    undergroundSystem.getAverageTime("Leyton", "Waterloo") // return 11.00000

    undergroundSystem.checkOut(10, "Waterloo", 38) // Customer 10 "Leyton" -> "Waterloo" in 38-24 = 14

    undergroundSystem.getAverageTime(
        "Leyton",
        "Waterloo"
    ) // return 12.00000. Three trips "Leyton" -> "Waterloo", (10 + 12 + 14) / 3 = 12

}