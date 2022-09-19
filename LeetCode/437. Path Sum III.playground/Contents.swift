public class TreeNode {
    public var val: Int
    public var left: TreeNode?
    public var right: TreeNode?
    public init(_ val: Int) {
        self.val = val
        self.left = nil
        self.right = nil
    }
}

class Solution {
    func pathSum(_ root: TreeNode?, _ sum: Int) -> Int {
        var count = 0
        calculateNode(sumMem: [], node: root, count: &count, target: sum)
        return count
    }
    
    private func calculateNode(sumMem: [Int], node: TreeNode?, count: inout Int, target: Int) {
        print("calculate \(node), sumMem = \(sumMem), count = \(count)")
        
        guard let node = node else {
            return
        }
        
        var newSumMem = sumMem.map { $0 + node.val }
        newSumMem.append(node.val)
        count += newSumMem.filter { $0 == target }.count
        
        calculateNode(sumMem: newSumMem, node: node.left, count: &count, target: target)
        calculateNode(sumMem: newSumMem, node: node.right, count: &count, target: target)
    }
}

let node1 = TreeNode(1)
let nodeMinus2 = TreeNode(-2)
let node3 = TreeNode(3)

let node3_2 = TreeNode(3)
node3_2.left = node3
node3_2.right = nodeMinus2
let node2 = TreeNode(2)
node2.left = node1
let node11 = TreeNode(11)

let node5 = TreeNode(5)
node5.left = node3_2
node5.right = node2
let nodeMinus3 = TreeNode(-3)
nodeMinus3.left = node11

let node10 = TreeNode(10)
node10.left = node5
node10.right = nodeMinus3

let s = Solution()
s.pathSum(node10, 8)
