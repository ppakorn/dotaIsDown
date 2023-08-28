package leetcode

class Solution210 {
    // Re-use from Solution207
    fun findOrder(numCourses: Int, prerequisites: Array<IntArray>): IntArray {
        val starters = (0 until numCourses).toMutableSet()

        val pre = mutableMapOf<Int, MutableSet<Int>>()
        val next = mutableMapOf<Int, MutableSet<Int>>()
        val visited = mutableSetOf<Int>()
        val order = mutableListOf<Int>()
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
                order.add(c)
                if (next[c] != null) {
                    queue.addAll(next[c]!! - visited)
                }
            }
        }

        return if (visited.size == numCourses) {
            order.toIntArray()
        } else {
            IntArray(0)
        }
    }
}