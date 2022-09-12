package advent2020

data class Node(
    val value: Int,
    var next: Node?
) {
    override fun toString(): String {
        return value.toString()
    }
}
