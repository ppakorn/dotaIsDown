package arise

class ReverseInt {
//    fun solution(N_arg: Int) {
//        val s = N_arg.toString()
//        print(s.reversed().toInt())
//    }

    fun solution(N_arg: Int) {
        var N: Int = N_arg
        var enable_print: Int

        enable_print = N % 10
        while (N > 0) {
            if (enable_print == 0 && N % 10 != 0) {
                enable_print = 1
            }
            if (enable_print > 0) {
                print(N % 10)
            }
            N = N / 10
        }
    }
}

fun main() {
    val s = ReverseInt()
//    s.solution(54321)
//    println()
//    s.solution(2100)
//    println()
    s.solution(819)
}