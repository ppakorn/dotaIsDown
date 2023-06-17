package leetcode

class Solution68 {
    fun fullJustify(words: Array<String>, maxWidth: Int): List<String> {
        val result = mutableListOf<String>()
        var i = 0
        while (i < words.size) {
            val j = getWords(words, maxWidth, i)
            val slice = words.slice(i until j)
            val s = if (j < words.size && slice.size > 1) {
                addSpaces(slice, maxWidth)
            } else {
                adjustLastLine(slice, maxWidth)
            }
            result.add(s)
            i = j
        }

        return result
    }

    private fun getWords(words: Array<String>, maxWidth: Int, firstIndex: Int): Int {
        // return last index exclusive
        var l = 0
        var i = firstIndex
        while (i < words.size) {
            l += words[i].length
            if (l > maxWidth) {
                return i
            }
            // add space
            l += 1

            i += 1
        }
        return i
    }

    private fun addSpaces(words: List<String>, maxWidth: Int): String {
        val length = words.fold(0) { acc, w -> acc + w.length }
        val base = (maxWidth - length) / (words.size - 1)
        val baseSpace = " ".repeat(base)
        val extra = (maxWidth - length) % (words.size - 1)
        var extraUsed = 0
        var s = words[0]
        for (i in 1 until words.size) {
            s += baseSpace
            if (extraUsed < extra) {
                s += " "
                extraUsed += 1
            }
            s += words[i]
        }
        return s
    }

    private fun adjustLastLine(words: List<String>, maxWidth: Int): String {
        var s = words.joinToString(" ")
        val spaceCount = maxWidth - s.length
        s += " ".repeat(spaceCount)
        return s
    }
}

fun main() {
    val s = Solution68()
    var words = arrayOf("This", "is", "an", "example", "of", "text", "justification.")
    println(s.fullJustify(words, 16))
    words = arrayOf("What","must","be","acknowledgment","shall","be")
    println(s.fullJustify(words, 16))
}