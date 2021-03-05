class Solution841 {
    fun canVisitAllRooms(rooms: List<List<Int>>): Boolean {
        val n = rooms.size
        val visited = mutableSetOf<Int>()
        visitRooms(rooms, listOf(0), visited)
        return visited.size == n
    }

    private fun visitRooms(
        rooms: List<List<Int>>,
        toVisit: List<Int>,
        visited: MutableSet<Int>
    ) {
        if (toVisit.isEmpty()) {
            return
        }

        val newToVisit = toVisit
            .flatMap { rooms[it] }
            .distinct()
            .filter { !visited.contains(it) }
        visited.addAll(toVisit)
        visitRooms(rooms, newToVisit, visited)
    }
}

fun main() {
    val s = Solution841()
//    val rooms1 = listOf(
//        listOf(1),
//        listOf(2),
//        listOf(3),
//        listOf()
//    )
//    println(s.canVisitAllRooms(rooms1))
    val rooms2 = listOf(
        listOf(1, 3),
        listOf(0, 3, 1),
        listOf(2),
        listOf(0)
    )
    println(s.canVisitAllRooms(rooms2))
}