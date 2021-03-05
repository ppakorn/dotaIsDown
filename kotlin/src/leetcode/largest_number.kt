class SolutionLGN {
    fun largestNumber(nums: IntArray): String {
        val comparator = Comparator { s1: String, s2: String ->
            if (s1 == s2) return@Comparator 0
            if (s1 + s2 > s2 + s1) return@Comparator -1
            return@Comparator 1
        }
        val n = nums.map { it.toString() }.sortedWith(comparator)

        return if (n[0] == "0") {
            "0"
        } else {
            n.joinToString("")
        }
    }
}

fun main() {
    val s = SolutionLGN()
    println(s.largestNumber(intArrayOf(3,4)))
    println(s.largestNumber(intArrayOf(9,50)))
    println(s.largestNumber(intArrayOf(3,30)))
    println(s.largestNumber(intArrayOf(3,30,34,5,9,91,50,506)))
    println(s.largestNumber(intArrayOf(121,12)))
    println(s.largestNumber(intArrayOf(0,0)))
    println(s.largestNumber(intArrayOf(830,8308)))
//    println(s.largestNumber(intArrayOf(9051,5526,2264,5041,1630,5906,6787,8382,4662,4532,6804,4710,4542,2116,7219,8701,8308,957,8775,4822,396,8995,8597,2304,8902,830,8591,5828,9642,7100,3976,5565,5490,1613,5731,8052,8985,2623,6325,3723,5224,8274,4787,6310,3393,78,3288,7584,7440,5752,351,4555,7265,9959,3866,9854,2709,5817,7272,43,1014,7527,3946,4289,1272,5213,710,1603,2436,8823,5228,2581,771,3700,2109,5638,3402,3910,871,5441,6861,9556,1089,4088,2788,9632,6822,6145,5137,236,683,2869,9525,8161,8374,2439,6028,7813,6406,7519)))
}