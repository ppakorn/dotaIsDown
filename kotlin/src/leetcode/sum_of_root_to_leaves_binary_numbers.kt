class SolutionSRL {
    fun sumRootToLeaf(root: TreeNode?): Int {
        if (root == null) return 0
        return sumRootToLeaf(root, 0)
    }

    private fun sumRootToLeaf(root: TreeNode, current: Int): Int {
        val newCurrent = current * 2 + root.`val`
        if (root.left == null && root.right == null) {
            return newCurrent
        }

        val left = root.left?.let {
            sumRootToLeaf(it, newCurrent)
        } ?: 0
        val right = root.right?.let {
            sumRootToLeaf(it, newCurrent)
        } ?: 0
        return left + right
    }
}

fun main() {
    val a0 = TreeNode(1)
    val b0 = TreeNode(0)
    val b1 = TreeNode(1)
    val c0 = TreeNode(0)
    val c1 = TreeNode(1)
    val c2 = TreeNode(0)
    val c3 = TreeNode(1)
//    a0.left = b0
//    a0.right = b1
    b0.left = c0
    b0.right = c1
    b1.left = c2
    b1.right = c3
    val s = SolutionSRL()
    println(s.sumRootToLeaf(a0))
}