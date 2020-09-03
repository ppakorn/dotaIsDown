class SolutionFizzBuzz {
    fun fizzBuzz(n: Int): List<String> {
        val result = mutableListOf<String>()
        (1..n).forEach {
            when {
                it % 15 == 0 -> result.add("FizzBuzz")
                it % 5 == 0 -> result.add("Buzz")
                it % 3 == 0 -> result.add("Fizz")
                else -> result.add("$it")
            }
        }
        return result
    }
}

fun main() {
//    val s = SolutionFizzBuzz()
//    println(s.fizzBuzz(15))
    val list1 = listOf(1,2,3,4,5)
    val list2 = listOf(1,2,3,4,5)
    println(list1 == list2)
}