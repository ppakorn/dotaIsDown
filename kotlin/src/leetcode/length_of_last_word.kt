class SolutionLLW {
    fun lengthOfLastWord(s: String): Int {
        return s.trim().split("\\s+".toRegex()).map { it.length }.last()
    }
}

fun main() {
    val s = SolutionLLW()
    println(s.lengthOfLastWord("     a     bb     "))
}