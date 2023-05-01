class Solution {
    func singleNonDuplicate(_ nums: [Int]) -> Int {
        return singleElement(nums[0..<nums.count])
    }
    
    // input array must be odd numbers
    func singleElement(_ array: ArraySlice<Int>) -> Int {
        let count = array.count
        if count == 1 { return array[array.startIndex] }
        
        let center = (array.endIndex - array.startIndex) / 2 + array.startIndex
        let smallSizeIsEven = center % 2 == 0
        
        if array[center] == array[center+1] {
            return smallSizeIsEven ?
                singleElement(array[center+2..<array.endIndex]) :
                singleElement(array[array.startIndex...center-1])
        } else if array[center] == array[center-1] {
            return smallSizeIsEven ?
                singleElement(array[array.startIndex...center-2]) :
                singleElement(array[center+1..<array.endIndex])
        } else {
            return array[center]
        }
    }
}

let solution = Solution()
solution.singleNonDuplicate([1,1,2,3,3,4,4,8,8])
solution.singleNonDuplicate([3,3,7,7,10,11,11])
solution.singleNonDuplicate([1])
solution.singleNonDuplicate([3,3,7,7,10,11,11])
solution.singleNonDuplicate([0,1,1,2,2,5,5])
