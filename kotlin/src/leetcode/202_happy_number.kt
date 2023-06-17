package leetcode

class Solution202 {
    private val happy = mutableSetOf<Int>()
    fun isHappy(n: Int): Boolean {
        if (happy.contains(n)) return true

        val visited = mutableSetOf(n)
        var i = n
        while (i > 1) {
            i = i.toString().map { it.toString().toInt() * it.toString().toInt() }.sum()
            if (visited.contains(i)) {
                return false
            }
            visited.add(i)
        }
        happy.addAll(visited)
        return true
    }
}

fun main() {
    val s = Solution202()
    println(s.isHappy(19))
}