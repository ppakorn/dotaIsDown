class SolutionSOLF {
    fun sumOfLeftLeaves(root: TreeNode?): Int {
        return sumLeft(root, false)
    }

    private fun sumLeft(node: TreeNode?, isLeft: Boolean): Int {
        if (node == null) return 0

        if (node.left == null && node.right == null) {
            return if (isLeft) {
                node.`val`
            } else {
                0
            }
        }

        return sumLeft(node.left, true) + sumLeft(node.right, false)
    }
}

fun main() {
    val node1 = TreeNode(1)
    val node2 = TreeNode(2)
    val node3 = TreeNode(3)
    val node4 = TreeNode(4)
    val node5 = TreeNode(5)

    node1.left = node2
    node1.right = node3
    node2.left = node4
    node2.right = node5

    val s = SolutionSOLF()
    println(s.sumOfLeftLeaves(node1))

    println(s.sumOfLeftLeaves(null))

    val a1 = TreeNode(3)
    val a2 = TreeNode(9)
    val a3 = TreeNode(20)
    val a4 = TreeNode(15)
    val a5 = TreeNode(7)
    a1.left = a2
    a1.right = a3
    a3.left = a4
    a3.right = a5
    println(s.sumOfLeftLeaves(a1))
}