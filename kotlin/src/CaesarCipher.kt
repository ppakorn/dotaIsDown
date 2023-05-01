fun caesarCipher(s: String, k: Int): String {
    // Write your code here
    return s.map { c ->
        if (c.isLetter()) {
            val starter = if (c.isUpperCase()) {
                'A'
            } else {
                'a'
            }
            val offset = c - starter
            val newOffset = (offset + k) % 26
            starter + newOffset
        } else {
            c
        }
    }.joinToString("")
}

fun main() {
    val s = "There's-a-starman"
    println(caesarCipher(s, 3))
}