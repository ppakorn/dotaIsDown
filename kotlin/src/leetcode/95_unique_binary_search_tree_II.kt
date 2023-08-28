package leetcode

class Solution95 {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun generateTrees(n: Int): List<TreeNode?> {
        return recur((1..n).toList())
    }

    private fun recur(remaining: List<Int>): List<TreeNode?> {
        if (remaining.isEmpty()) {
            return listOf(null)
        }

        val result = mutableListOf<TreeNode>()
        remaining.forEach { x ->
            val leftRemaining = remaining.filter { it < x }
            val leftNodes = recur(leftRemaining)
            val rightRemaining = remaining.filter { it > x }
            val rightNodes = recur(rightRemaining)

            leftNodes.forEach { l ->
                rightNodes.forEach { r ->
                    val mid = TreeNode(x)
                    mid.left = l
                    mid.right = r
                    result.add(mid)
                }
            }
        }
        return result
    }
}

fun main() {
    val s = Solution95()
    val r = s.generateTrees(3)
    println(r.size)
}
