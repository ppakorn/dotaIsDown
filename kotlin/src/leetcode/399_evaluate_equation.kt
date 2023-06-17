package leetcode

class Solution399 {
    fun calcEquation(equations: List<List<String>>, values: DoubleArray, queries: List<List<String>>): DoubleArray {
        val map = createMap(equations, values)
        val results = queries.map {
            query(map, it, 1.0, emptySet()) ?: -1.0
        }.toDoubleArray()
        println(results.contentToString())
        return results
    }

    private fun createMap(equations: List<List<String>>, values: DoubleArray): Map<String, MutableMap<String, Double>> {
        val map = mutableMapOf<String, MutableMap<String, Double>>()
        equations.forEachIndexed { i, strings ->
            val a = strings[0]
            val b = strings[1]
            val v = values[i]

            if (map[a] == null) {
                map[a] = mutableMapOf()
            }
            if (map[b] == null) {
                map[b] = mutableMapOf()
            }

            map[a]!![b] = v
            map[a]!![a] = 1.0
            map[b]!![a] = 1 / v
            map[b]!![b] = 1.0
        }

        return map
    }

    private fun query(
        map: Map<String, MutableMap<String, Double>>,
        strings: List<String>,
        previous: Double,
        visited: Set<String>
    ): Double? {
        val start = strings[0]
        val final = strings[1]

        if (visited.contains(start)) {
            return null
        }

        if (map[start]?.get(final) != null) {
            return map[start]!![final]!! * previous
        }

        if (map[start] == null) {
            return null
        }

        val newVisited = visited.toMutableSet()
        newVisited.add(start)
        for (next in map[start]!!.keys) {
            val child = query(map, listOf(next, final), previous * map[start]!![next]!!, newVisited)
            if (child != null) {
                return child
            }
        }

        return null
    }

    fun transformInput(str: String): List<List<String>> {
        return str
            .removePrefix("[")
            .removeSuffix("]")
            .split("],")
            .map { a ->
                val aa = a.removePrefix("[").removeSuffix("]")
                if (aa.isEmpty()) {
                    emptyList()
                } else {
                    aa.split(",")
                }
            }
    }
}

fun main() {
    val s = Solution399()
    var equations = s.transformInput("[[a,b],[b,c]]")
    var values = listOf(2.0,3.0).toDoubleArray()
    var queries = s.transformInput("[[a,c],[b,a],[a,e],[a,a],[x,x]]")
    s.calcEquation(equations, values, queries)

    equations = s.transformInput("[[a,b],[b,c],[bc,cd]]")
    values = listOf(1.5,2.5,5.0).toDoubleArray()
    queries = s.transformInput("[[a,c],[c,b],[bc,cd],[cd,bc]]")
    s.calcEquation(equations, values, queries)

    equations = s.transformInput("[[a,b]]")
    values = listOf(0.5).toDoubleArray()
    queries = s.transformInput("[[a,b],[b,a],[a,c],[x,y]]")
    s.calcEquation(equations, values, queries)
}