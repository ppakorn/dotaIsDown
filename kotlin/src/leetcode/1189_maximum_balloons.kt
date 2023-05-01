class Solution1189 {
    fun maxNumberOfBalloons(text: String): Int {
        val a = IntArray(26) { 0 }
        text.forEach {
            a[it - 'a'] += 1
        }

        val min1 = minOf(a[0], a[1], a[13])
        val min2 = minOf(a[11] / 2, a[14] / 2)
        return minOf(min1, min2)
    }
}

fun main() {
    val s = Solution1189()
    println(s.maxNumberOfBalloons("nlaebolko"))
    println(s.maxNumberOfBalloons("loonbalxballpoon"))
}