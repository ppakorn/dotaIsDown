package leetcode

class Solution100 {
    class TreeNode(var `val`: Int) {
        var left: TreeNode? = null
        var right: TreeNode? = null
    }
    fun isSameTree(p: TreeNode?, q: TreeNode?): Boolean {
        if (p == null && q == null) {
            return true
        } else if (p == null || q == null) {
            return false
        }

        return p.`val` == q.`val` &&
                isSameTree(p.left, q.left) &&
                isSameTree(p.right, q.right)
    }
}