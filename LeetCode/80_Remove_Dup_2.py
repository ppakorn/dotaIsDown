class Solution(object):
    def removeDuplicates(self, nums):
        """
        :type nums: List[int]
        :rtype: int
        """
        if len(nums) == 0:
            return []

        ans = [nums[0]]
        previous = nums[0]
        dup = False
        for i in range(1, len(nums)):
            n = nums[i]
            if n != previous:
                ans.append(n)
                previous = n
                dup = False
                continue
            if not dup:
                ans.append(n)
                dup = True

        return ans

print Solution().removeDuplicates([1,1,1,2,2,3])