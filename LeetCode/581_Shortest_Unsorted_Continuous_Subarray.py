# [1, 3, 6, 4, 8, 10, 2, 9, 15]
# [1, 2,, 6, 3, 9, 7, 8, 10
# [1, 8, 7, 3, 4, 2]
# [1, 7, 6, 2, 3]
# [1, 8, 9, 5, 3]
# [3, 4, 1, 6, 8]
# [8, 7, 2, 5, 1, 6]
# [1, 2, 4, 8, 9, 10, 13]
from typing import List


class Solution:
    def findUnsortedSubarray(self, nums: List[int]) -> int:
        length = len(nums)
        minimum = nums[length - 1]
        begin = -1
        maximum = nums[0]
        end = length
        for i in range(1, length):
            minimum = min(minimum, nums[length - 1 - i])
            if minimum < nums[length - 1 - i]:
                begin = length - 1 - i
            maximum = max(maximum, nums[i])
            if maximum > nums[i]:
                end = i

        if begin == -1:
            return 0
        return end - begin + 1

    def findUnsortedSubarray2(self, nums: List[int]) -> int:
        minimum = self.find_wrong_min(nums)
        if minimum is None:
            return 0
        min_correct_index = self.find_wrong_min_correct_index(nums, minimum)

        maximum = self.find_wrong_max(nums)
        max_correct_index = self.find_wrong_max_correct_index(nums, maximum)
        return max_correct_index - min_correct_index + 1

    @staticmethod
    def find_wrong_min(nums):
        last = nums[0]
        minimum = None
        index = -1
        for i, num in enumerate(nums):
            if num < last:
                minimum = num
                index = i
                break
            last = num

        if index == -1:
            return None

        for i in range(index + 1, len(nums)):
            if nums[i] < minimum:
                minimum = nums[i]

        return minimum

    @staticmethod
    def find_wrong_min_correct_index(nums, minimum):
        for i in range(len(nums)):
            if nums[i] > minimum:
                return i

    @staticmethod
    def find_wrong_max(nums):
        length = len(nums)
        last = nums[length - 1]
        maximum = None
        index = length
        for i, num in reversed(list(enumerate(nums))):
            if num > last:
                maximum = num
                index = i
                break
            last = num

        for i in range(index - 1, -1, -1):
            if nums[i] > maximum:
                maximum = nums[i]

        return maximum

    @staticmethod
    def find_wrong_max_correct_index(nums, maximum):
        for i in range(len(nums) - 1, -1, -1):
            if nums[i] < maximum:
                return i


s = Solution()
print(s.findUnsortedSubarray2([1, 2, 3, 6, 4, 8, 10, 2, 9, 15]))
print(s.findUnsortedSubarray2([1, 2, 6, 3, 9, 7, 8, 10]))
print(s.findUnsortedSubarray2([1, 8, 7, 3, 4, 2]))
print(s.findUnsortedSubarray2([1, 7, 6, 2, 3]))
print(s.findUnsortedSubarray2([1, 8, 9, 5, 3]))
print(s.findUnsortedSubarray2([3, 4, 1, 6, 8]))
print(s.findUnsortedSubarray2([8, 7, 2, 5, 1, 6]))
print(s.findUnsortedSubarray2([1, 2, 4, 8, 9, 10, 13]))

