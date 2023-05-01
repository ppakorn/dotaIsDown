import kotlin.math.abs

class SolutionCDP3 {
    fun containsNearbyAlmostDuplicate(nums: IntArray, k: Int, t: Int): Boolean {
        if (k == 0) return false

        val bst = BinarySearchTreeForCDP3()
        (0 until k).forEach {
            val x = nums[it]
            if (bst.searchSimilar(x, t)) {
                return true
            }
            bst.insert(x)
        }

        (k until nums.size).forEach {
            val x = nums[it]
            if (bst.searchSimilar(x, t)) {
                return true
            }
            bst.delete(nums[it - k])
            bst.insert(x)
        }

        return false
    }
}

class BinarySearchTreeForCDP3: BinarySearchTree() {
    fun searchSimilar(x: Int, t: Int): Boolean {
        return searchSimilar(root, x, t)
    }

    private fun searchSimilar(node: TreeNode?, x: Int, t: Int): Boolean {
        if (node == null) return false
        val dif = node.`val`.toLong() - x.toLong()
        if (abs(dif) <= t) return true

        return if (x > node.`val`)
            searchSimilar(node.right, x, t)
        else
            searchSimilar(node.left, x, t)
    }
}

fun main() {
    val s = SolutionCDP3()
//    println(s.containsNearbyAlmostDuplicate(intArrayOf(1,2,3,1), 2, 0))
//    println(s.containsNearbyAlmostDuplicate(intArrayOf(1,2,3,1), 3, 0))
//    println(s.containsNearbyAlmostDuplicate(intArrayOf(1,0,1,0,1), 1, 0))
//    println(s.containsNearbyAlmostDuplicate(intArrayOf(1,5,9,1,5,9), 2, 4))
    println(s.containsNearbyAlmostDuplicate(intArrayOf(-1, 2147483647), 1, 2147483647))
}