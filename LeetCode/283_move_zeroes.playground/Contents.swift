class Solution {
    func moveZeroes(_ nums: inout [Int]) {
        let count = nums.count
        nums = nums.filter { $0 != 0 }
        let zeroCount = count - nums.count
        nums.append(contentsOf: [Int](repeating: 0, count: zeroCount))
    }
}

var a = [0,1,0,3,12]
Solution().moveZeroes(&a)
