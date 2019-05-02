class Solution {
    func productExceptSelf(_ nums: [Int]) -> [Int] {
        var lproduct = 1
        var l: [Int] = []
        
        for num in nums {
            l.append(lproduct)
            lproduct *= num
        }
        
        lproduct = 1
        for (i, num) in nums.enumerated().reversed() {
            l[i] *= lproduct
            lproduct *= num
        }
        
        return l
    }
}

Solution().productExceptSelf([1,2,3,4])
