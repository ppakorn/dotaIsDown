import java.lang.StringBuilder
import java.time.Instant

fun main() {
    testString(10000000)
}

fun testString(loop: Int = 1000000) {
    val strs = listOf(
        "Yab3pFNuOaj5kkaoHnU3",
        "XTytF6YxPYO4JCMtdssU",
        "VM81gwXXLBJ0s4WTp4qI",
        "8o0axqQitrTSHQeqA2Sn",
        "NnTH76pm2knSBg7QDUkS",
        "b7DOw8YEQmrmXV0aAvQk",
        "U34DP1Ur64OcOO2ElgOP",
        "JF35SxZtBH2lDummNULj",
        "IYf1mCi67bB3n0N5PmDC",
        "V4Cf8pbDyqIY5tW5dgoG",
        "PDxIuSNKyyTWOINZ24lR",
        "iRciQVw4RyO2ad9xssPg",
        "V0cvlsfNbOZKGewN1I9m",
        "PcaIF0y6iGcPChrGMA63",
        "K2B6pmOdanJm7LpYPQfE",
        "Nk14lbmLkcPpS9WDN47s",
        "sdl1TiLRo09L8eOUy8VG",
        "mPAgxHGQ1mdudqRcQwzu",
        "pHXSOq6H9yGdxxhCClAJ",
        "9vXlb0MB1xaevHb43amH"
    )

    val start1 = System.currentTimeMillis()
    for (i in 0 until loop) {
        val str = strs[0] + strs[1] + strs[2] + strs[3] + strs[4] +
            strs[5] + strs[6] + strs[7] + strs[8] + strs[9] +
            strs[10] + strs[11] + strs[12] + strs[13] + strs[14] +
            strs[15] + strs[16] + strs[17] + strs[18] + strs[19]
        str
    }
    val stop1 = System.currentTimeMillis()
    println("One concat = ${stop1 - start1}")

//    val start2 = System.currentTimeMillis()
//    for (i in 0 until loop) {
//        var str = ""
//        for (s in strs) {
//            str += s
//        }
//        str
//    }
//    val stop2 = System.currentTimeMillis()
//    println("Multiple concat = ${stop2 - start2}")

    val start3 = System.currentTimeMillis()
    for (i in 0 until loop) {
        val builder = StringBuilder()
        for (s in strs) {
            builder.append(s)
        }
        val str = builder.toString()
        str
    }
    val stop3 = System.currentTimeMillis()
    println("StringBuilder = ${stop3 - start3}")

    val start4 = System.currentTimeMillis()
    for (i in 0 until loop) {
        val str = "${strs[0]}${strs[1]}${strs[2]}${strs[3]}${strs[4]}${strs[5]}${strs[6]}${strs[7]}${strs[8]}${strs[9]}${strs[10]}${strs[11]}${strs[12]}${strs[13]}${strs[14]}${strs[15]}${strs[16]}${strs[17]}${strs[18]}${strs[19]}"
        str
    }
    val stop4 = System.currentTimeMillis()
    println("Template = ${stop4 - start4}")
}