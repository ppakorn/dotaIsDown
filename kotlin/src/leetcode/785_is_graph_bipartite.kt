package leetcode

class Solution785 {
//    fun isBipartite(graph: Array<IntArray>): Boolean {
//        graph.forEachIndexed { i, d ->
//            val l = d.toList()
//            if (set1.isEmpty()) {
//                set1.add(i)
//                set2.addAll(l)
//            } else if (set1.contains(i)) {
//                set2.addAll(l)
//            } else if (set2.isEmpty()) {
//                set2.add(i)
//                set1.addAll(l)
//            } else if (set2.contains(i)) {
//                set1.addAll(l)
//            } else {
//                if (l.intersect(set1).isNotEmpty()) {
//                    set1.addAll(l)
//                    set2.add(i)
//                } else {
//                    set2.addAll(l)
//                    set1.add(i)
//                }
//            }
//
//            if (set1.intersect(set2).isNotEmpty()) {
//                return false
//            }
//        }

//        println(set1)
//        println(set2)
//        return true
//    }

    private val set1 = mutableSetOf<Int>()
    private val set2 = mutableSetOf<Int>()
    private val visited = mutableSetOf<Int>()
    private var result = true

    fun isBipartite(graph: Array<IntArray>): Boolean {
        graph.forEachIndexed { i, _ ->
            run(graph, i)
        }
        return result
    }

    private fun run(graph: Array<IntArray>, i: Int) {
        if (visited.contains(i) || !result) return
        visited.add(i)

        val l = graph[i].toList()
        if (set1.contains(i)) {
            set2.addAll(l)
        } else if (set2.contains(i)) {
            set1.addAll(l)
        } else {
            set1.add(i)
            set2.addAll(l)
        }
        l.forEach { run(graph, it) }

        if (set1.intersect(set2).isNotEmpty()) {
            result = false
            return
        }
    }

    fun transformInput(str: String): Array<IntArray> {
        return str
            .split("],")
            .map { a ->
                val aa = a.removePrefix("[").removeSuffix("]")
                if (aa.isEmpty()) {
                    IntArray(0)
                } else {
                    aa.split(",").map { it.toInt() }.toIntArray()
                }
            }
            .toTypedArray()
    }
}

fun main() {
    val s = Solution785()
    val g = s.transformInput("[3],[2,4],[1],[0,4],[1,3]")
    println(s.isBipartite(g))
}

