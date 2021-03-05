class Solution5519 {
    fun reorderSpaces(text: String): String {
        val words = text.trim().split("\\s+".toRegex())
        val spaces = text.count { it == ' ' }

        if (words.size == 1) {
            var result = text.trim()
            while (result.length < text.length) {
                result += " "
            }
            return result
        }

        val s = spaces / (words.size - 1)

        val separator = " ".repeat(s)
        var result = words.joinToString(separator)

        while (result.length < text.length) {
            result += " "
        }
        return result
    }
}

fun main() {
    val s = Solution5519()
    println(s.reorderSpaces("  walks  udp package   into  bar a"))
    println(s.reorderSpaces("  this   is  a sentence "))
    println(s.reorderSpaces(" practice   makes   perfect"))
    println(s.reorderSpaces("hello    world   "))
    println(s.reorderSpaces("a"))
    println(s.reorderSpaces("      a"))

}