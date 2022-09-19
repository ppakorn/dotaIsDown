class Solution(object):
    def maxCoins(self, nums):
        l = len(nums)
        if l == 0:
            return 0

        if l == 1:
            return nums[0]

        max_coin = 0
        for i in range(0, l):
            coin = 0
            if i == 0:
                coin = nums[i] * nums[i+1]
            elif i == l - 1:
                coin = nums[i-1] * nums[i]
            else:
                coin = nums[i-1] * nums[i] * nums[i+1]

            temp_nums = nums[:i] + nums[i+1:]
            coin += self.maxCoins(temp_nums)

            if coin > max_coin:
                max_coin = coin

        return max_coin



print Solution().maxCoins([1,9,3])

# [3,1,9]
# [3,1,5,8]
# []
# [1,3,9]
# [23,56,1,34,2,5,78]
# [312]
# [35,16,83,87,84,59,48,41,20,54]