package leetcode

import kotlin.math.max

class Solution767 {
    fun reorganizeString(s: String): String {
        val map = mutableMapOf<Char, Int>()
        s.forEach {
            map[it] = (map[it] ?: 0) + 1
        }

        if (map.values.max()!! > (s.length + 1) / 2) {
            return ""
        }

        var result = ""
        val keys = map.keys.toList().sortedByDescending { map[it] }
        var i = 0
        var j = 1
        var t = 0

        while ((t % 2 == 0 && i < keys.size) || (t % 2 == 1 && j < keys.size)) {
            when (t % 2) {
                0 -> {
                    val c = keys[i]
                    result += c
                    map[c] = map[c]!! - 1
                    if (map[c] == 0) {
                        i = max(i, j) + 1
                    }
                }
                1 -> {
                    val c = keys[j]
                    result += c
                    map[c] = map[c]!! - 1
                    if (map[c] == 0) {
                        j = max(i, j) + 1
                    }
                }
            }
            t += 1
        }

        val k = if (i < keys.size && map[keys[i]]!! > 0) {
            i
        } else if (j < keys.size && map[keys[j]]!! > 0) {
            j
        } else {
            -1
        }

        if (k > -1 && map[keys[k]] != null && map[keys[k]]!! > 0) {
            var p1 = result.substring(0, map[keys[k]]!!)
            val p2 = result.substring(map[keys[k]]!!)
            p1 = p1.map { it.toString() + keys[k] }.joinToString("")
            result = p1 + p2
        }

        return result
    }
}

fun main() {
    val s = Solution767()
    println(s.reorganizeString("aaabbbbbbbbbeeefffggg"))
}