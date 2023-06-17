package leetcode

class Solution547 {
    fun findCircleNum(isConnected: Array<IntArray>): Int {
        val n = isConnected.size
        var provinceCount = 0
        val visited = BooleanArray(n) { false }

        for (i in 0 until n) {
            if (visited[i]) {
                continue
            }
            connectProvince(i, visited, isConnected)
            provinceCount += 1
        }
        return provinceCount
    }

    private fun connectProvince(city: Int, visited: BooleanArray, isConnected: Array<IntArray>) {
        visited[city] = true
        isConnected[city].forEachIndexed { otherCity, connected ->
            if (connected == 1 && !visited[otherCity]) {
                connectProvince(otherCity, visited, isConnected)
            }
        }
    }
}

fun main() {
    val s = Solution547()
    val input = transform2DIntArray("[[1,1,0],[1,1,0],[0,0,1]]")
    println(s.findCircleNum(input))
}