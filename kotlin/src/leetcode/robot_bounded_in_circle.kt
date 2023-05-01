class SolutionRBC {
    fun isRobotBounded(instructions: String): Boolean {
        var x = 0
        var y = 0
        var direction = 0                   // 0 = up, 1 = left, 2 = down, 3 = right
        instructions.forEach {
            when (it) {
                'L' -> direction = Math.floorMod(direction + 1, 4)
                'R' -> direction = Math.floorMod(direction - 1, 4)
                'G' -> {
                    when (direction) {
                        0 -> y += 1
                        1 -> x -= 1
                        2 -> y -= 1
                        3 -> x += 1
                    }
                }
            }
        }

        return direction != 0 || (x == 0 && y == 0)
    }
}

fun main() {
    val s = SolutionRBC()
    println(s.isRobotBounded("GLRLLGLL"))
}