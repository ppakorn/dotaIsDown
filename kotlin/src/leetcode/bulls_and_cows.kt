class SolutionBAC {
    fun getHint(secret: String, guess: String): String {
        var bull = 0
        var s = secret.toCharArray()
        var g = guess.toCharArray()
        s.forEachIndexed { i, c ->
            if (c == guess[i]) {
                bull += 1
                s[i] = '#'
                g[i] = '#'
            }
        }

        var cow = 0
        s.filter { it != '#' }.forEach { c ->
            val i = g.indexOfFirst { it == c }
            if (i > -1) {
                cow += 1
                g[i] = '#'
            }
        }

        return "${bull}A${cow}B"
    }
}

fun main() {
    val s = SolutionBAC()
    println(s.getHint("1807", "7810"))
    println(s.getHint("1123", "0111"))
    println(s.getHint("2221", "1212"))
}