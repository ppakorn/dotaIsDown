package leetcode

class Solution1557 {
    fun findSmallestSetOfVertices(n: Int, edges: List<List<Int>>): List<Int> {
        val hasInEdges = edges.map {
            it[1]
        }.toSet()
        val all = (0 until n).toSet()
        return all.minus(hasInEdges).toList()
    }

    fun transformInput(str: String): List<List<Int>> {
        return str
            .split("],")
            .map { a ->
                a.removePrefix("[").removeSuffix("]").split(",").map { it.toInt() }
            }
    }
}

fun main() {
    val s = Solution1557()
    val n = 5
    val e = "[0,1],[2,1],[3,1],[1,4],[2,4]"
    val edges = s.transformInput(e)
    println(s.findSmallestSetOfVertices(n, edges))
}



