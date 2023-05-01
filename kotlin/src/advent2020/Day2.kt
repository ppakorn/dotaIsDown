package advent2020

import java.io.File

fun main() {
    var count = 0
    File("/Users/ampos/Documents/dota_is_down/LeetCode/leetcode-kotlin/src/advent2020/Day2.input").forEachLine { line ->
        val a = line.split(";")
        val pos1 = a[0].toInt()
        val pos2 = a[1].toInt()
        val char = a[2]
        val pass = a[3]

//        val charCount = pass.count { char.contains(it) }
//        if (charCount in min..max) {
//            count += 1
//        }

        val pos1Correct = char.contains(pass[pos1 - 1])
        val pos2Correct = char.contains(pass[pos2 - 1])
        if (pos1Correct xor pos2Correct) {
            count += 1
        }
    }
    print(count)
}