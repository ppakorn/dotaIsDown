package advent2021

class Day21 {
//    var dieCount = 0
//    var nextDiePoint = 1
//    var position = mutableListOf(8, 4)
//    var points = mutableListOf(0, 0)
//    var player1Turn = true
//
//    fun execute() {
//        while (points[0] < 1000 && points[1] < 1000) {
//            val index = if (player1Turn) {
//                0
//            } else {
//                1
//            }
//            val steps = getSteps()
//            var newPosition = (position[index] + steps) % 10
//            if (newPosition == 0)
//                newPosition = 10
//            position[index] = newPosition
//            points[index] += position[index]
//            player1Turn = !player1Turn
//        }
//    }
//
//    private fun getSteps(): Int {
//        val steps = nextDiePoint * 3 + 3
//        nextDiePoint += 3
//        dieCount += 3
//        return steps
//    }
//
//    fun answer(): Int {
//        return min(points[0], points[1]) * dieCount
//    }

    val chances = mapOf(
        3 to 1,
        4 to 3,
        5 to 6,
        6 to 7,
        7 to 6,
        8 to 3,
        9 to 1
    )
    var player1Won = 0L
    var player2Won = 0L
    val goal = 21
    fun executeRecursive(positions: List<Int>, points: List<Int>, playerTurn: Int, pathCount: Long) {
        if (points[0] >= goal) {
            player1Won += pathCount
            return
        }
        if (points[1] >= goal) {
            player2Won += pathCount
            return
        }

        chances.forEach { (step, path) ->
            var newPosition = (positions[playerTurn] + step) % 10
            if (newPosition == 0) {
                newPosition = 10
            }

            val newPositions = if (playerTurn == 0) {
                listOf(newPosition, positions[1])
            } else {
                listOf(positions[0], newPosition)
            }
            val newPoints = if (playerTurn == 0) {
                listOf(points[0] + newPosition, points[1])
            } else {
                listOf(points[0], points[1] + newPosition)
            }
            val newTurn = (playerTurn + 1) % 2
            val newPathCount = pathCount * path
            executeRecursive(newPositions, newPoints, newTurn, newPathCount)
        }

    }
}

fun main() {
    val d = Day21()
    d.executeRecursive(
        listOf(8, 4),
        listOf(0, 0),
        0,
        1
    )
    println(d.player1Won)
    println(d.player2Won)
}