class Solution(object):
    def maxNumber(self, nums1, nums2, k):
        """
        :type nums1: List[int]
        :type nums2: List[int]
        :type k: int
        :rtype: List[int]
        """
        if k == 0:
            return []

        max1 = -1
        max2 = -1
        nnums1 = nums1
        nnums2 = nums2
        for i in range(len(nums1)):
            if len(nums2) + (len(nums1) - i) < k:
                break
            if nums1[i] > max1:
                max1 = nums1[i]
                nnums1 = nums1[i+1:]

        for i in range(len(nums2)):
            if len(nums1) + (len(nums2) - i) < k:
                break
            if nums2[i] > max2:
                max2 = nums2[i]
                nnums2 = nums2[i+1:]

        if max1 > max2:
            return [max1] + self.maxNumber(nnums1, nums2, k-1)
        elif max2 > max1:
            return [max2] + self.maxNumber(nums1, nnums2, k - 1)

        




print(Solution().maxNumber([3,9], [8,9], 3))