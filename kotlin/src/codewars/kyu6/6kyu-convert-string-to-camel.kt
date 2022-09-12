package codewars

import kotlin.test.assertEquals

private fun toCamelCase(str:String):String {
    if (str.isBlank()) return str
    val sub = str.split("-", "_")
    val capSub = sub.mapIndexed { i, s ->
        if (i == 0) {
            s
        } else {
            s.capitalize()
        }
    }
    return capSub.joinToString("")
}

fun main() {
    assertEquals("", toCamelCase(""))
    assertEquals("theStealthWarrior", toCamelCase("the_stealth_warrior"))
    assertEquals("TheStealthWarrior", toCamelCase("The-Stealth-Warrior"))
    assertEquals("ABC", toCamelCase("A-B-C"))
}