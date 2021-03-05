import kotlin.test.assertEquals

class SolutionCVN {
    fun compareVersion(version1: String, version2: String): Int {
        val v1 = version1.split(".").map { it.toInt() }.toMutableList()
        val v2 = version2.split(".").map { it.toInt() }.toMutableList()

        while (v1.size < v2.size) {
            v1.add(0)
        }
        while (v2.size < v1.size) {
            v2.add(0)
        }

        var i = 0
        while (i < v1.size) {
            when {
                v1[i] > v2[i] -> return 1
                v1[i] < v2[i] -> return -1
            }
            i += 1
        }

        return 0
    }
}

fun main() {
    val s = SolutionCVN()
    assertEquals(-1, s.compareVersion("0.1", "1.1"))
    assertEquals(1, s.compareVersion("1.0.0.00.1", "1"))
    assertEquals(-1, s.compareVersion("7.5.2.4", "7.5.3"))
    assertEquals(0, s.compareVersion("1.01", "1.001"))
    assertEquals(0, s.compareVersion("1.0.0.0", "1"))
}