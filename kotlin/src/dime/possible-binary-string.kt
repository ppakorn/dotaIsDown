package dime

fun allPossibleBinary(s: String): List<String> {
    var result = listOf("")

    s.forEach { c ->
        result = if (c == '?') {
            result.flatMap { listOf(it + '0', it + '1') }
        } else {
            result.map { it + c }
        }
    }

    return result
}

fun main() {
    println(allPossibleBinary("???"))
    println(allPossibleBinary(""))
}