open class BinarySearchTree {

    var root: TreeNode? = null

    fun search(x: Int): TreeNode? {
        return search(x, root)
    }

    private fun search(x: Int, node: TreeNode?): TreeNode? {
        if (node == null || node.`val` == x) return node

        return if (x > node.`val`) {
            search(x, node.right)
        } else {
            search(x, node.left)
        }
    }

    fun insert(x: Int) {
        root = insert(x, root)
    }

    private fun insert(x: Int, node: TreeNode?): TreeNode {
        if (node == null) {
            return TreeNode(x)
        }

        if (x > node.`val`) {
            node.right = insert(x, node.right)
        } else if (x < node.`val`) {
            node.left = insert(x, node.left)
        }
        return node
    }

    fun delete(x: Int) {
        root = delete(root, x)
    }

    private fun delete(node: TreeNode?, x: Int): TreeNode? {
        if (node == null) return null

        when {
            node.`val` > x -> {
                node.left = delete(node.left, x)
            }
            node.`val` < x -> {
                node.right = delete(node.right, x)
            }
            else -> {
                when {
                    node.left == null -> {
                        return node.right
                    }
                    node.right == null -> {
                        return node.left
                    }
                    else -> {
                        val newRoot = findMax(node.left!!)
                        node.`val` = newRoot.`val`
                        node.left = delete(node.left, newRoot.`val`)
                    }
                }
            }
        }

        return node
    }

    private fun findMax(root: TreeNode): TreeNode {
        if (root.right != null) {
            return findMax(root.right!!)
        }
        return root
    }

    fun printDFS() {
        printDFS(root)
    }

    private fun printDFS(node: TreeNode?) {
        if (node == null) return
        printDFS(node.left)
        println(node.`val`)
        printDFS(node.right)
    }
}

fun main() {
    val t = BinarySearchTree()
    t.insert(6)
    t.insert(16)
    t.insert(21)
    t.insert(12)
    t.insert(4)
    t.insert(8)
    t.insert(9)
    t.insert(24)
    t.printDFS()
    t.delete(8)
    t.printDFS()
}