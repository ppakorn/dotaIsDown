class SolutionFTD {
    fun findTheDifference(s: String, t: String): Char {
        return 'a' + t.map { it - 'a' }.sum() - s.map { it - 'a' }.sum()
    }
}