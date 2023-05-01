package advent2020

import java.io.File
import java.lang.Exception

class Day16 {
    var mem = mutableMapOf<Int, MutableList<Int>>()
    var i = 1

    private val rulesStr = listOf(
        "departure location: 37-479 or 485-954",
        "departure station: 35-653 or 668-974",
        "departure platform: 36-225 or 240-955",
        "departure track: 27-854 or 879-966",
        "departure date: 46-828 or 834-951",
        "departure time: 36-591 or 613-957",
        "arrival location: 26-770 or 785-955",
        "arrival station: 33-695 or 712-961",
        "arrival platform: 39-369 or 391-968",
        "arrival track: 34-331 or 354-963",
        "class: 27-63 or 89-951",
        "duration: 42-545 or 561-956",
        "price: 50-250 or 258-955",
        "route: 41-150 or 168-953",
        "row: 49-356 or 366-970",
        "seat: 39-429 or 441-974",
        "train: 30-180 or 206-958",
        "type: 37-577 or 588-950",
        "wagon: 38-137 or 148-956",
        "zone: 36-909 or 927-970"
//        "class: 0-1 or 4-19",
//        "row: 0-5 or 8-19",
//        "seat: 0-13 or 16-19"
    )
    private val rules = mutableListOf<MutableList<Int>>()

    fun execute() {
        processRules()
        val values = mutableListOf<List<Int>>()
        File("/Users/ampos/Documents/dota_is_down/LeetCode/leetcode-kotlin/src/advent2020/Day16.input").forEachLine { line ->
            val a = line.split(",").map { it.toInt() }
            a.forEach value@ { value ->
                rules.forEach rule@ { rule ->
                    if (value in rule[0]..rule[1] || value in rule[2]..rule[3]) {
                        return@value
                    }
                }
                return@forEachLine
            }

            values.add(a)
        }

        val answers = mutableListOf<Pair<Int, MutableList<Int>>>()
        for (column in 0 until values[0].count()) {
            val possibleRules = (0 until values[0].count()).toMutableList()
            values.forEach { row ->
                val v = row[column]
                (possibleRules.count() - 1 downTo 0).forEach { ruleIndex ->
                    val rule = rules[possibleRules[ruleIndex]]
                    if (v !in rule[0]..rule[1] && v !in rule[2]..rule[3]) {
                        possibleRules.removeAt(ruleIndex)
                    }
                }
            }

            answers.add(Pair(column, possibleRules))
        }

        val used = mutableSetOf<Int>()
        answers.sortBy { it.second.count() }
        answers.forEach {
            it.second.removeAll(used)
            used.addAll(it.second)
        }
        answers.sortBy { it.second[0] }
//        answers.sortBy { it.first }

        answers.forEach {
            println(it)
        }

        val myTicket = listOf(97,101,149,103,137,61,59,223,263,179,131,113,241,127,53,109,89,173,107,211)
        println(myTicket[4] * myTicket[0])
        println(myTicket[16] * myTicket[17])
        println(myTicket[7] * myTicket[10])
    }

    private fun processRules() {
        rulesStr.forEach { str ->
            val ruleList = mutableListOf<Int>()
            val a = str.split(": ")[1].split(" or ")
            a.forEach {
                val b = it.split("-")
                ruleList.add(b[0].toInt())
                ruleList.add(b[1].toInt())
            }
            rules.add(ruleList)
        }
    }
}

fun main() {
    val d = Day16()
    d.execute()
}
