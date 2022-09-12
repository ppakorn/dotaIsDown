package codewars.kyu5

import java.io.File

class Int32ToIpv4 {
    fun longToIP(ip: UInt): String {
        val binary = ip.toString(2).padStart(32, '0')
        val a = binary.substring(0, 8).toInt(2)
        val b = binary.substring(8, 16).toInt(2)
        val c = binary.substring(16, 24).toInt(2)
        val d = binary.substring(24, 32).toInt(2)
        return "$a.$b.$c.$d"
    }
}

fun main() {
//    val s = Int32ToIpv4()
//    println(s.longToIP(2149583361u))
    println(testRead())
}

fun testRead(): String {
    val fi = File("/Users/ampos/Desktop/ttttt")
    println(fi)
    val s = fi.readText()
    fi.delete()
    fi.delete()
    fi.delete()
    return s
}
