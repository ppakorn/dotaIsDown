package advent2020

import com.sun.org.apache.xpath.internal.operations.Bool
import java.io.File

class Day19 {

    private val rules = mutableMapOf<Int, List<List<Int>>>()
    private val solvedRules = mutableMapOf<Int, Set<String>>()

    private val firstAnswers = setOf(
        "bbabbbbaabbababbaabbbaba",
        "bababbbabbbbbbbaabababaa",
        "abbbabbbaabaaaaabbbabbba",
        "abbaabbabababbaaaaabbbab",
        "babaaaabbabaaaababbbbbba",
        "aaaaabbbbabbbbabbaaaaaba",
        "aabbbbabaaabaaaaaaaaaaab",
        "aabaabbbabababbbaababbaa",
        "ababbbabbbabbbaaabbabbab",
        "babaaaaaabbabbaabaaabaab",
        "baababbababaabbbbaabaabb",
        "abaaabaabbaababaabbaabab",
        "baabbaaabbbbbbaaabbabaab",
        "abbbbbaabbbbbaaaabbbbbba",
        "baababbaaaaaaabaabababaa",
        "aababbbaabbbbaaaaababbbb",
        "baaabbbbaaaaaabaabbbbbbb",
        "bbbbaaaaaaabbbbbaaaabaab",
        "ababaaaabaabababbababaaa",
        "aabbbbabaaabbabaababbaaa",
        "ababbbababaaaababaabbabb",
        "aabbaabaabaaaaaaaabbbaba",
        "bbabbaabbaabbabaabbaaabb",
        "abababababaabaaaaabbbaba",
        "aabbaaaaaabababbabbbaabb",
        "bbbbbaaaabbbbaabaaabbaab",
        "ababaaaababbabbbabababba",
        "bbababbbbabbbabaabbabbab",
        "babaaaababbaabbbbbabbaaa",
        "bbaaababaaaaababaababaab",
        "bbbabaaaaaaabaaabaabaaab",
        "aaaababbbbabbbaabaabbbbb",
        "aabababbababaaabaabbbbbb",
        "bbababbaabbabbaaabaaabbb",
        "ababbbaaaabbabbabbbbaaab",
        "bbbbaaaaaabbabbaaabaabab",
        "aaabbbbbabbaaaaabbbbabaa",
        "babbaaaabaababbaaaabaabb",
        "aababbbababbabbbabaaaabb",
        "aabbbabbaaababbabababaab",
        "baabbaaabbbaaabbbaaababa",
        "aababbabbabbaabaaaaaaaab",
        "aabbbbaaaabaabbbaaaabbba",
        "babbaaabaaaababbbaababaa",
        "bbbbbbbaabbabbaabbbbabba",
        "aaabbabbbbababbabbaabbaa",
        "aabbbbaaaabaaabaabbabbba",
        "bababbaaaaaabbaabbbbaaab",
        "bbabababbbaaaababaaababb",
        "aaaaabbbbabbaabaabaabbaa",
        "babaaaaaaababaaababbbbba",
        "aaaaabababaabaaaabaaaabb",
        "bbabbbbaaabaaababaaaaabb",
        "babbbaababbbbaabaabbbbbb",
        "abbbaaababaaaaaaababbbba",
        "babbaaababbbbaabbaabbbab",
        "bbbaabbbabaabaaabaaabbba",
        "babaabaaababaaaabababaaa",
        "babababbababbbabbaaaaabb",
        "ababaaabbbaabbbababbabaa",
        "bbaaaababbabbababbbbbaba",
        "abbababaabbababbabbbbbba",
        "aabbabbaaaaababbbaabbbbb",
        "baabbabaababababbaaaabab",
        "abbaabbaaabbaabaaaabbaaa",
        "aabbaabbbaaabbbbabaaaaab",
        "abaabaababababbbabababba",
        "baaabbabababaaaaabbaaabb",
        "abbaaaaababbabbabbbbbbab",
        "ababaaabbaababbbbbbbbbab",
        "aabaabaaaababbbabaabbbbb",
        "aaabbabbabbababaababaaba",
        "aaababbaabaaaabaabaaabba",
        "abaaaababaaabbbbbabbbbaa",
        "aaabaaaabbbbaaaabbaaaabb",
        "aabbbabbabaaaabaaaababaa",
        "babbaabababaabbbbbaabbaa",
        "aabbaabbbbababababbabbba",
        "ababbbabbabbabbbaaabbbab",
        "bababbbaabbaabbabbbaabab",
        "aabbabaababbbabaaaaaaaab",
        "abbabbaabaaaaaaabbbbbaab",
        "bbaababbabaaaaaaaaabbaaa",
        "babbbbabababaabbbabbaabb",
        "aabababbbabaababbaababaa",
        "babaabbbbbabaaaabbaabaab",
        "babababbbbaababbbabaabba",
        "abaababbbbabaaaaabbbaabb",
        "aaaabbabbabbabbaaaababaa",
        "bbbabababaabababaaaabaab",
        "bbbabbbbbaabababbbaabbab",
        "abaaaababbaabbbabbaaaabb",
        "abbbabbbabababbbababbbba",
        "bbbbbbbaabbbaaaabaaaaabb",
        "aabbaabababaaababaabaaab",
        "ababbbaaaabbabaabbaaabba",
        "aaaabbaabbaababaabbaaaba",
        "abbbbbaababaababbbaabbab",
        "bbbabbaaababbbbbbabbaabb",
        "bbabbaabaabbaaaabbbbaabb",
        "abaaaaaaababbbabbbbbbbab",
        "aaabaababbabbababbaabaab",
        "baababbaabaababbabbabbab",
        "babbbababbbbbbbabbabbbbb",
        "abbaabbaaaaabbaaaaababab",
        "bbbababaaaaaaaaabbaaabba",
        "aabbabbabbaaaaabbaabbbab",
        "aaaabbaabaaaaaaabbbbabaa",
        "babbbbbbaaababbbabbabaaa",
        "abbbabbbbabbaaaabbbaabab",
        "abbbbaaaaaabbbbbababbabb",
        "bbaaaaaababaaaabbbabaaba",
        "baabbbaabbaababababababa",
        "abbabbaaaabbbbbaabaabbbb",
        "bbbababbbaabbbaabbabbbab",
        "bbbbbaaaabaababbabbbabba",
        "bbaaaabaabaabaaaabbbaabb",
        "ababbbbbaaabbababbaaabba",
        "babbaaabbbaaababbbbbbbbb",
        "bbbababbbbababbbaaabbbab",
        "abbbabaabaabaabaabaaabba",
        "ababaaababaaabaaaabaaabb",
        "bbabaaaaaabbaaaaaaaaaaab",
        "abbababbbabbaaaaaabbbaab",
        "abbabababaaabbbbbbbaaaab",
        "bbbabbaabbaaaabaaaabaabb",
        "babbbabaaaababbbbaababaa",
        "aaaaaaaaabbbaaaaabaaabab",
        "bbaabbbababbaaabbababbbb",
        "abbbbaabbbaaaababbaabaaa",
        "babbabbbbaaaaaaabbabaabb",
        "aababbabbbaaababaaabbbaa",
        "bbbaabbbabbbabaabbabbbab",
        "aabaabaabbabaaaabbbbabba",
        "aaaabaaabbbabbbbaaabaaab",
        "aabbbabbaaabbbbbaaaaabaa"
    )

    fun execute(): Int {
        solveRules()
        var count = 0
        File("/Users/ampos/Documents/dota_is_down/LeetCode/leetcode-kotlin/src/advent2020/Day19.input").forEachLine { line ->
            if (firstAnswers.contains(line)) {
                count += 1
                return@forEachLine
            }

            val length8 = solvedRules[8]!!.first().length
            val length42 = solvedRules[42]!!.first().length
            val length31 = solvedRules[31]!!.first().length
            val length11 = length42 + length31
            for (i in 1 until line.length / length8) {
                val prefix = line.substring(0, length8 * i)
                val postfix = line.substring(length8 * i, line.length)
                if (postfix.length.rem(length11) != 0) {
                    continue
                }

                if (check8(prefix) && check11(postfix)) {
                    count += 1
                    return@forEachLine
                }
            }
        }
        return count
    }

    private fun solveRules() {
        solvedRules[5] = setOf("a")
        solvedRules[72] = setOf("b")

//        solvedRules[1] = setOf("a")
//        solvedRules[14] = setOf("b")

        File("/Users/ampos/Documents/dota_is_down/LeetCode/leetcode-kotlin/src/advent2020/Day19-Rule.input").forEachLine { line ->
            val a = line.split(": ")
            val key = a[0].toInt()
            val b = a[1].split(" | ")
            val r = b.map { c ->
                c.split(" ").map { it.toInt() }.toList()
            }
            rules[key] = r
        }

        solveRuleRecursive(0)
    }

    private fun solveRuleRecursive(ruleNumber: Int): Set<String> {
        if (solvedRules[ruleNumber] != null) {
            return solvedRules[ruleNumber]!!
        }

        val strings = rules[ruleNumber]!!.map { rule ->
            rule.fold(setOf("")) { acc, subRule ->
                val s = solveRuleRecursive(subRule)
                acc.flatMap { a ->
                    s.map { subStr ->
                        a + subStr
                    }
                }.toSet()
            }
        }

        val solved = strings.fold(emptySet<String>()) { acc, set ->
            acc union set
        }
        solvedRules[ruleNumber] = solved
        return solved
    }

    private fun check8(s: String): Boolean {
        val length8 = solvedRules[8]!!.first().length
        val length = s.length
        if (length.rem(length8) != 0) {
            return false
        }

        for (i in 0 until length / length8) {
            val sub = s.substring(length8 * i, length8 * (i + 1))
            if (!solvedRules[8]!!.contains(sub)) {
                return false
            }
        }

        return true
    }

    private fun check11(s: String): Boolean {
        val length42 = solvedRules[42]!!.first().length
        val length31 = solvedRules[31]!!.first().length
        val length = s.length
        if (length.rem(length42 + length31) != 0) {
            return false
        }

        for (i in 0 until length / (length42 + length31)) {
            val prefix = s.substring(length42 * i, length42 * (i + 1))
            val postfix = s.substring(length - (length31 * (i + 1)), length - (length31 * i))
            if (!(solvedRules[42]!!.contains(prefix) && solvedRules[31]!!.contains(postfix))) {
                return false
            }
        }

        return true
    }

}

fun main() {
    val d = Day19()
    println(d.execute())
}