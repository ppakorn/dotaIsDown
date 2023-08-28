package leetcode

class Solution101 {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }

    fun isSymmetric(root: TreeNode?): Boolean {
        if (root == null) return true
        return isSym(root.left, root.right)
    }

    private fun isSym(left: TreeNode?, right: TreeNode?): Boolean {
        if (left == null && right == null) {
            return true
        }
        if (left?.`val` != right?.`val`) {
            return false
        }

        return isSym(left!!.right, right!!.left) && isSym(left.left, right.right)
    }
}