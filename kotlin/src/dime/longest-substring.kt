package dime

fun longestSubstringWithoutRepeat(s: String): String {
    val mem = mutableSetOf<Char>()
    var longest = ""

    var temp = ""
    s.forEach { c ->
        // If duplicate, restart at after the left one
        // For example, abca -> restart to bca
        if (mem.contains(c)) {
            val list = temp.split(c)
            list[0].forEach { mem.remove(it) }
            temp = list[1] + c
        } else {
            temp += c
            mem.add(c)
            if (temp.length > longest.length) {
                longest = temp
            }
        }
    }

    // Compare last string
    if (temp.length > longest.length) {
        longest = temp
    }

    return longest
}

fun main() {
    println(longestSubstringWithoutRepeat("abcabcbb"))
    println(longestSubstringWithoutRepeat("aaaaba"))
    println(longestSubstringWithoutRepeat("bbbbb"))
    println(longestSubstringWithoutRepeat("tmmzuxt"))
    println(longestSubstringWithoutRepeat("xxxxaxabcdexxabcxx"))
    println(longestSubstringWithoutRepeat("x"))
    println(longestSubstringWithoutRepeat(""))
}