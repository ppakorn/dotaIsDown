import math


class Solution(object):
    # number_of_bits = [0]
    #
    # def countBits(self, num):
    #     while len(self.number_of_bits) - 1 < num:
    #         new = map(lambda x: x + 1, self.number_of_bits.copy())
    #         self.number_of_bits += new
    #
    #     return self.number_of_bits[0:num+1]

    def countBits(self, num):
        result = [0]
        latest = 0
        for i in range(1, num + 1):
            if math.log2(i).is_integer():
                result.append(1)
                latest = i
                continue
            result.append(result[i - latest] + 1)

        return result


s = Solution()
print(s.countBits(2))
print(s.countBits(5))
print(s.countBits(20))
