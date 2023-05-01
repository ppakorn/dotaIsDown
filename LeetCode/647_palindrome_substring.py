class Solution(object):
    def countSubstrings(self, s):
        """
        :type s: str
        :rtype: int
        """
        even = self.numberOfEvenLengthPalindrome(s)
        odd = self.numberOfOddLengthPalindrome(s)
        return even + odd


    def numberOfEvenLengthPalindrome(self, s):
        length = len(s)
        count = 0
        for i in range(length - 1):
            # check longest offset even palindrome
            longestOffset = min(i, length - i - 2)
            if self.isPalindrome(s[i - longestOffset: i + 1 + longestOffset + 1]):
                count += longestOffset + 1
            else:
                j = 0
                while (i - j >= 0) & ((i + 1) + j < length):
                    # even-length palindrome with i as a left-center like baab
                    even = s[i - j:(i + 1) + j + 1]
                    if self.isPalindrome(even):
                        count += 1
                        j += 1
                    else:
                        break
        return count


    def numberOfOddLengthPalindrome(self, s):
        length = len(s)
        count = 0
        for i in range(length):
            # check longest offset odd palindrome
            longestOffset = min(i, length - i - 1)
            if self.isPalindrome(s[i-longestOffset: i+longestOffset+1]):
                count += longestOffset + 1
            else:
                j = 0
                while (i - j >= 0) & (i + j < length):
                    odd = s[i-j:i+j+1]
                    if self.isPalindrome(odd):
                        count += 1
                        j += 1
                    else:
                        break
        return count


    def isPalindrome(self, s):
        length = len(s)
        for i in range(length / 2):
            if s[i] != s[length - i - 1]:
                return False
        return True


s = Solution()
# print s.countSubstrings("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa")

print s.countSubstrings("baaba")

