class Solution(object):

    one = ['Zero', 'One', 'Two', 'Three', 'Four', 'Five', 'Six', 'Seven', 'Eight', 'Nine']
    ten = ['', '', 'Twenty', 'Thirty', 'Forty', 'Fifty', 'Sixty', 'Seventy', 'Eighty', 'Ninety']
    teen = ['Ten', 'Eleven', 'Twelve', 'Thirteen', 'Fourteen', 'Fifteen', 'Sixteen', 'Seventeen', 'Eighteen', 'Nineteen']
    thirdName = ['', 'Thousand', 'Million', 'Billion']

    def english(self, hundred, ten, one, name):
        if hundred == 0 and ten == 0 and one == 0:
            return ''

        ans = ''
        if hundred > 0:
            ans += self.one[hundred] + ' Hundred '

        if ten == 1:
            ans += self.teen[one] + ' '
        else:
            if ten > 0:
                ans += self.ten[ten] + ' '
            if one > 0:
                ans += self.one[one] + ' '

        ans += name
        return ans.strip()


    def numberToWords(self, num):
        if num == 0:
            return 'Zero'

        N = [int(d) for d in str(num)]
        N.reverse()
        ans = ''

        for i in range(0, len(N), 3):
            one = N[i]
            ten = 0
            hundred = 0
            if i + 2 < len(N):
                hundred = N[i + 2]
            if i + 1 < len(N):
                ten = N[i + 1]

            name = self.thirdName[int(i/3)]

            ans = self.english(hundred, ten, one, name) + ' ' + ans
            ans = ans.strip()

        return ans

print(Solution().numberToWords(0))
print(Solution().numberToWords(1))
print(Solution().numberToWords(5))
print(Solution().numberToWords(10))
print(Solution().numberToWords(13))
print(Solution().numberToWords(100))
print(Solution().numberToWords(1000))
print(Solution().numberToWords(10000))
print(Solution().numberToWords(100000))
print(Solution().numberToWords(1000001))
print(Solution().numberToWords(10010000001))
print(Solution().numberToWords(5981270005))