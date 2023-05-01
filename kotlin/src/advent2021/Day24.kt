package advent2021

import java.io.File

class Day24 {
    private val filename = "/Users/ampos/Documents/dota_is_down/kotlin/src/advent2021/Day24-test.input"
    var w = 0
    var x = 0
    var y = 0
    var z = 0
    var k = listOf<Int>()
    var i = 0

    val instructions = """
        inp w
        mul x 0
        add x z
        mod x 26
        div z 1
        add x 12
        eql x w
        eql x 0
        mul y 0
        add y 25
        mul y x
        add y 1
        mul z y
        mul y 0
        add y w
        add y 1
        mul y x
        add z y
        inp w
        mul x 0
        add x z
        mod x 26
        div z 1
        add x 13
        eql x w
        eql x 0
        mul y 0
        add y 25
        mul y x
        add y 1
        mul z y
        mul y 0
        add y w
        add y 9
        mul y x
        add z y
        inp w
        mul x 0
        add x z
        mod x 26
        div z 1
        add x 12
        eql x w
        eql x 0
        mul y 0
        add y 25
        mul y x
        add y 1
        mul z y
        mul y 0
        add y w
        add y 11
        mul y x
        add z y
        inp w
        mul x 0
        add x z
        mod x 26
        div z 26
        add x -13
        eql x w
        eql x 0
        mul y 0
        add y 25
        mul y x
        add y 1
        mul z y
        mul y 0
        add y w
        add y 6
        mul y x
        add z y
        inp w
        mul x 0
        add x z
        mod x 26
        div z 1
        add x 11
        eql x w
        eql x 0
        mul y 0
        add y 25
        mul y x
        add y 1
        mul z y
        mul y 0
        add y w
        add y 6
        mul y x
        add z y
        inp w
        mul x 0
        add x z
        mod x 26
        div z 1
        add x 15
        eql x w
        eql x 0
        mul y 0
        add y 25
        mul y x
        add y 1
        mul z y
        mul y 0
        add y w
        add y 13
        mul y x
        add z y
        inp w
        mul x 0
        add x z
        mod x 26
        div z 26
        add x -14
        eql x w
        eql x 0
        mul y 0
        add y 25
        mul y x
        add y 1
        mul z y
        mul y 0
        add y w
        add y 13
        mul y x
        add z y
        inp w
        mul x 0
        add x z
        mod x 26
        div z 1
        add x 12
        eql x w
        eql x 0
        mul y 0
        add y 25
        mul y x
        add y 1
        mul z y
        mul y 0
        add y w
        add y 5
        mul y x
        add z y
        inp w
        mul x 0
        add x z
        mod x 26
        div z 26
        add x -8
        eql x w
        eql x 0
        mul y 0
        add y 25
        mul y x
        add y 1
        mul z y
        mul y 0
        add y w
        add y 7
        mul y x
        add z y
        inp w
        mul x 0
        add x z
        mod x 26
        div z 1
        add x 14
        eql x w
        eql x 0
        mul y 0
        add y 25
        mul y x
        add y 1
        mul z y
        mul y 0
        add y w
        add y 2
        mul y x
        add z y
        inp w
        mul x 0
        add x z
        mod x 26
        div z 26
        add x -9
        eql x w
        eql x 0
        mul y 0
        add y 25
        mul y x
        add y 1
        mul z y
        mul y 0
        add y w
        add y 10
        mul y x
        add z y
        inp w
        mul x 0
        add x z
        mod x 26
        div z 26
        add x -11
        eql x w
        eql x 0
        mul y 0
        add y 25
        mul y x
        add y 1
        mul z y
        mul y 0
        add y w
        add y 14
        mul y x
        add z y
        inp w
        mul x 0
        add x z
        mod x 26
        div z 26
        add x -6
        eql x w
        eql x 0
        mul y 0
        add y 25
        mul y x
        add y 1
        mul z y
        mul y 0
        add y w
        add y 7
        mul y x
        add z y
        inp w
        mul x 0
        add x z
        mod x 26
        div z 26
        add x -5
        eql x w
        eql x 0
        mul y 0
        add y 25
        mul y x
        add y 1
        mul z y
        mul y 0
        add y w
        add y 1
        mul y x
        add z y
    """.trimIndent()

    fun reset() {
        w = 0
        x = 0
        y = 0
        z = 0
        i = 0
    }

    fun execute(n: Long): Boolean {
        k = n.toString().padStart(14, '0').toCharArray().map { it - '0' }
        if (k.contains(0)) return false

        reset()
        instructions.lines().forEach { line ->
            val a = line.split(" ")
            when (a[0]) {
                "inp" -> input(a[1])
                else -> cal(a[0], a[1], a[2])
            }
        }
        return z == 0
    }

    fun input(to: String) {
        val v = k[i]
        i += 1
        when (to) {
            "w" -> w = v
            "x" -> x = v
            "y" -> y = v
            "z" -> z = v
        }
    }

    fun cal(operation: String, a: String, b: String) {
        val ai = getValue(a)
        val bi = getValue(b)
        val new = when(operation) {
            "add" -> ai + bi
            "mul" -> ai * bi
            "div" -> ai / bi
            "mod" -> ai % bi
            "eql" -> if (ai == bi) 1 else 0
            else -> throw Exception("not support")
        }
        when (a) {
            "w" -> w = new
            "x" -> x = new
            "y" -> y = new
            "z" -> z = new
        }
    }

    fun getValue(s: String): Int {
        return when (s) {
            "w" -> w
            "x" -> x
            "y" -> y
            "z" -> z
            else -> s.toInt()
        }
    }
}

fun main() {
    val d = Day24()
//    run {
//        (99_999_999_999_999 downTo 0).forEach {
//            if (d.execute(it)) {
//                println(it)
//                return@run
//            }
//        }
//    }
//    val max = 96_979_989_692_495
    val min = 51316214181141
    println(d.execute(min))
}