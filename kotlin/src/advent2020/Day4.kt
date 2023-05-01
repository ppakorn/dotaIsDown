package advent2020

import java.io.File

class Day4() {

    fun execute() {
        var count = 0
        var passport = mutableMapOf<String, String>()
        File("/Users/ampos/Documents/dota_is_down/LeetCode/leetcode-kotlin/src/advent2020/Day4.input").forEachLine { line ->
            if (line.isEmpty()) {
                if (isCorrect(passport)) {
                    count += 1
                }
                passport = mutableMapOf()
                return@forEachLine
            }

            val fields = line.split(" ")
            fields.forEach { field ->
                val f = field.split(':')
                passport[f[0]] = f[1]
            }
        }

        if (isCorrect(passport)) {
            count += 1
        }
        println(count)
    }

    private fun isCorrect(map: Map<String, String>): Boolean {
        val height = isCorrectHeight(map["hgt"])
        val hair = isCorrectHairColor(map["hcl"])
        val eye = isCorrectEyeColor(map["ecl"])
        val pid = isCorrectPassportId(map["pid"])
        return map["byr"] != null && map["byr"]!!.toInt() in 1920..2002 &&
                map["iyr"] != null && map["iyr"]!!.toInt() in 2010..2020 &&
                map["eyr"] != null && map["eyr"]!!.toInt() in 2020..2030 &&
                height && hair && eye && pid
    }

    private fun isCorrectHeight(height: String?): Boolean {
        val h = height ?: return false
        if (h.length !in 4..5) return false
        val unit = h.takeLast(2)
        val v = h.dropLast(2).toInt()

        if (unit == "cm") {
            return v in 150..193
        }

        if (unit == "in") {
            return v in 59..76
        }

        return false
    }

    private fun isCorrectHairColor(hairColor: String?): Boolean {
        val c = hairColor ?: return false
        return c.matches(Regex("^#[0-9a-f]{6}$"))
    }

    private fun isCorrectEyeColor(eyeColor: String?): Boolean {
        val c = eyeColor ?: return false
        return c == "amb" || c == "blu" || c == "brn" || c == "gry" ||
                c == "grn" || c == "hzl" || c == "oth"
    }

    private fun isCorrectPassportId(passport: String?): Boolean {
        val p = passport ?: return false
        return p.matches(Regex("^[0-9]{9}$"))
    }
}

fun main() {
    val d = Day4()
    d.execute()
}
