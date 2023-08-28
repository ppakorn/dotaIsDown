package leetcode

class Solution920 {
    private val mem = mutableMapOf<Pair<Int, Int>, Long>()
    private val mod: Long = 1_000_000_000 + 7
    private var n = 0L
    private var goal = 0L
    private var k = 0L

    fun numMusicPlaylists(n: Int, goal: Int, k: Int): Int {
        mem.clear()
        this.n = n.toLong()
        this.goal = goal.toLong()
        this.k = k.toLong()
        return f(goal, n).toInt()
    }

    // i คือจำนวนเพลงใน playlist
    // j คือใช้ j เพลงจากทั้งหมด n เพลง
    // f(i, j) = f(i - 1, j - 1) * (n - j + 1) + f(i - 1, j) * (j - k)
    // พจน์แรกคือ จากตอนแรกใช้ไป j - 1 เพลงสร้าง i - 1 ใน playlist เลือกเพลงใหม่ที่ยังไม่เคยใช้มา เพลงที่ยังไม่เคยใช้คือ n - j + 1 เพลง
    // พจน์สองคือ จากตอนแรกใช้ไป j เพลงสร้าง i - 1 ใน playlist เลือกเพลงที่เคยใช้ไปแล้วมา เพลงที่เคยใช้แล้ว reuse ได้คือ j - k เพลง
    private fun f(i: Int, j: Int): Long {
        if (i < 1 || j < 1) {
            return 0L
        }

        // ถ้าใช้ได้ 1 เพลง สร้าง 1 เพลง คือมี possibility ให้เลือก n เพลง
        if (i == 1 && j == 1) {
            return n
        }

        val pair = Pair(i, j)
        if (mem[pair] != null) {
            return mem[pair]!!
        }

        val p1 = (f(i - 1, j - 1) * (n - j + 1)) % mod
        val p2 = if (j - k > 0) {
            (f(i - 1, j) * (j - k)) % mod
        } else {
            0
        }
        val result = (p1 + p2) % mod
        mem[pair] = result
        return result
    }
}

fun main() {
    val s = Solution920()
    println(s.numMusicPlaylists(3, 3, 1))
    println(s.numMusicPlaylists(2, 3, 0))
    println(s.numMusicPlaylists(2, 3, 1))
}