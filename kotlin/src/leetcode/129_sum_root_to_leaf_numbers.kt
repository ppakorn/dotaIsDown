package leetcode

class Solution129 {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun sumNumbers(root: TreeNode?): Int {
        if (root == null) return 0
        return sum(root, "")
    }

    private fun sum(node: TreeNode, previous: String): Int {
        val n = previous + node.`val`
        if (node.left == null && node.right == null) {
            return n.toInt()
        }

        var l = 0
        var r = 0
        if (node.left != null) {
            l = sum(node.left!!, n)
        }
        if (node.right != null) {
            r = sum(node.right!!, n)
        }

        return l + r
    }
}

fun main() {
    val s = Solution129()
    val a = Solution129.TreeNode(1)
    val b = Solution129.TreeNode(2)
    val c = Solution129.TreeNode(3)
    a.left = b
    a.right = c
    println(s.sumNumbers(a))
}