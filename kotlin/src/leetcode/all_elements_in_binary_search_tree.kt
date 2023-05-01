class SolutionAEB {
    fun getAllElements(root1: TreeNode?, root2: TreeNode?): List<Int> {
        val list1 = mutableListOf<Int>()
        val list2 = mutableListOf<Int>()
        dfs(root1, list1)
        dfs(root2, list2)

        val result = mutableListOf<Int>()
        var i = 0
        var j = 0
        while (i < list1.size && j < list2.size) {
            if (list1[i] < list2[j]) {
                result.add(list1[i])
                i += 1
            } else {
                result.add(list2[j])
                j += 1
            }
        }

        if (i == list1.size) {
            while (j < list2.size) {
                result.add(list2[j])
                j += 1
            }
        }
        if (j == list2.size) {
            while (i < list1.size) {
                result.add(list1[i])
                i += 1
            }
        }

        return result
    }

    private fun dfs(node: TreeNode?, list: MutableList<Int>) {
        if (node == null) return
        dfs(node.left, list)
        list.add(node.`val`)
        dfs(node.right, list)
    }
}