class SolutionDNB {
    fun deleteNode(root: TreeNode?, key: Int): TreeNode? {
        if (root == null) return null

        when {
            root.`val` > key -> {
                root.left = deleteNode(root.left, key)
            }
            root.`val` < key -> {
                root.right = deleteNode(root.right, key)
            }
            else -> {
                when {
                    root.left == null -> {
                        return root.right
                    }
                    root.right == null -> {
                        return root.left
                    }
                    else -> {
                        val newRoot = findMax(root.left!!)
                        root.`val` = newRoot.`val`
                        root.left = deleteNode(root.left, newRoot.`val`)
                    }
                }
            }
        }

        return root
    }

    private fun findMax(root: TreeNode): TreeNode {
        if (root.right != null) {
            return findMax(root.right!!)
        }
        return root
    }
}

fun main() {
    val a5 = TreeNode(5)
    val a3 = TreeNode(3)
    val a2 = TreeNode(2)
    val a4 = TreeNode(4)
    val a6 = TreeNode(6)
    val a7 = TreeNode(7)
    a5.left = a3
    a5.right = a6
    a6.right = a7
    a3.left = a2
    a3.right = a4

    val b3 = TreeNode(3)
    val b1 = TreeNode(1)
    val b4 = TreeNode(4)
    val b2 = TreeNode(2)
    b3.left = b1
    b3.right = b4
    b1.right = b2

    val s = SolutionDNB()
    s.deleteNode(b3, 3)
}