package leetcode

class Solution207 {
    fun canFinish(numCourses: Int, prerequisites: Array<IntArray>): Boolean {
        val starters = (0 until numCourses).toMutableSet()

        val pre = mutableMapOf<Int, MutableSet<Int>>()
        val next = mutableMapOf<Int, MutableSet<Int>>()
        val visited = mutableSetOf<Int>()
        val queue = mutableSetOf<Int>()

        prerequisites.forEach {
            starters.remove(it[0])
            if (pre[it[0]] == null) {
                pre[it[0]] = mutableSetOf()
            }
            pre[it[0]]!!.add(it[1])

            if (next[it[1]] == null) {
                next[it[1]] = mutableSetOf()
            }
            next[it[1]]!!.add(it[0])
        }

        queue.addAll(starters)
        while (queue.isNotEmpty()) {
            val c = queue.random()
            queue.remove(c)

            if (visited.contains(c)) {
                continue
            }

            if (visited.containsAll(pre[c] ?: emptySet())) {
                visited.add(c)
                if (next[c] != null) {
                    queue.addAll(next[c]!! - visited)
                }
            }
        }

        return visited.size == numCourses
    }
}

fun main() {
    val s = Solution207()
    println(s.canFinish(3, transform2DIntArray("[[0,2],[1,2],[2,0]]")))
    println(s.canFinish(2, transform2DIntArray("[[0,2],[1,2],[2,0]]")))
}