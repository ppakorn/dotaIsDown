import Foundation

class Solution {
    func findMedianSortedArrays(_ nums1: [Int], _ nums2: [Int]) -> Double {
        return findMedianSortedArrays(nums1[0..<nums1.count], nums2[0..<nums2.count])
    }
    
    func findMedianSortedArrays(_ nums1: ArraySlice<Int>, _ nums2: ArraySlice<Int>) -> Double {

        if nums1.count < nums2.count {
            return findMedianSortedArrays(nums2, nums1)
        }
        
        if nums2.count == 0 {
            return median(nums1)
        }
        if nums2.count == 1 {
            if nums1.count <= 3 {
                return finalStep(nums1, nums2)
            } else {
                let mi = Int(medianIndex(nums1))
                let startIndex = nums1.count % 2 == 0 ? mi : mi - 1
                let subarray1 = nums1[startIndex...(mi+1)]
                return findMedianSortedArrays(subarray1, nums2)
            }
        } else if nums2.count == 2 {
            if nums1.count <= 4 {
                return finalStep(nums1, nums2)
            } else {
                let mi = Int(medianIndex(nums1)) - 1
                let endIndex = nums1.count % 2 == 0 ? mi + 3 : mi + 2
                let subarray1 = nums1[mi...endIndex]
                return findMedianSortedArrays(subarray1, nums2)
            }
        }
        
        let cutCount = (nums2.count - 1) / 2
        let med1 = median(nums1)
        let med2 = median(nums2)
        if med1 > med2 {
            let nn1 = removeGreaterPart(array: nums1, count: cutCount)
            let nn2 = removeLesserPart(array: nums2, count: cutCount)
            return findMedianSortedArrays(nn1, nn2)
        } else if med2 > med1 {
            let nn1 = removeLesserPart(array: nums1, count: cutCount)
            let nn2 = removeGreaterPart(array: nums2, count: cutCount)
            return findMedianSortedArrays(nn1, nn2)
        }
        return med1
    }
    
    func finalStep(_ nums1: ArraySlice<Int>, _ nums2: ArraySlice<Int>) -> Double {
        let arr = nums1 + nums2
        let sortedArr = arr.sorted()
        return median(sortedArr[0..<sortedArr.count])
    }
    
    func median(_ array: ArraySlice<Int>) -> Double {
        if array.count % 2 == 0 {
            let index1 = Int(medianIndex(array))
            let index2 = Int(medianIndex(array)) + 1
            return (Double(array[index1]) + Double(array[index2])) / 2
        } else {
            let index = Int(medianIndex(array))
            return Double(array[index])
        }
    }
    
    func medianIndex(_ array: ArraySlice<Int>) -> Double {
        return (Double(array.count) - 1) / 2.0 + Double(array.startIndex)
    }
    
    func removeLesserPart(array: ArraySlice<Int>, count: Int) -> ArraySlice<Int> {
        return array[(array.startIndex+count)..<array.endIndex]
    }
    
    func removeGreaterPart(array: ArraySlice<Int>, count: Int) -> ArraySlice<Int> {
        return array[array.startIndex..<(array.endIndex-count)]
    }
}
//
//Solution().findMedianSortedArrays([1], [2])
//Solution().findMedianSortedArrays([1, 3], [2])
//Solution().findMedianSortedArrays([1], [2, 8])
//Solution().findMedianSortedArrays([8, 10], [1, 12])
//Solution().findMedianSortedArrays([1, 2, 3], [4, 5])

//Solution().findMedianSortedArrays([1, 3, 4, 6, 7, 10], [2, 3, 5])
Solution().findMedianSortedArrays([],
                                  [1])

//
//let arr = Array(0...10)
//let arr2 = arr[3...6]
//Solution().removeLesserPart(array: arr2, count: 1)
//Solution().removeGreaterPart(array: arr2, count: 1)
//
//Solution().median(arr2)
