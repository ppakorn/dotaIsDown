package advent2021

class Day17 {
    private val xmin = 81
    private val xmax = 129
    private val ymin = -150
    private val ymax = -108
    private val uxmin = 13
//    private val xmin = 20
//    private val xmax = 30
//    private val ymin = -10
//    private val ymax = -5
//    private val uxmin = 6
    fun execute() {
        var count = 0
        (uxmin..xmax).forEach { ux ->
            (ymin..-ymin).forEach { uy ->
                var currentx = 0
                var currenty = 0
                var vx = ux
                var vy = uy
                while (currentx <= xmax && currenty >= ymin) {
                    currentx += vx
                    currenty += vy
                    vx = maxOf(vx - 1, 0)
                    vy -= 1
                    if (currentx in xmin..xmax && currenty in ymin..ymax) {
                        count += 1
                        break
                    }
                }
            }
        }
        println(count)
    }
}

fun main() {
    val d = Day17()
    d.execute()
}