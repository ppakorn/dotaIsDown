class Solution(object):
    order = [0, 2, 4, 6, 8, 5, 7, 3, 1, 9]
    text = ["zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"]

    def indexOf(self, mainStr, s):
        r = []
        for c in s:
            if c in mainStr:
                i = mainStr.index(c)
                if i in r:
                    temp = mainStr[i+1:]
                    i += temp.index(c) + 1
                r.append(i)
            else:
                return False
        return r

    def remove(self, mainStr, s):
        indexes = self.indexOf(mainStr, s)
        if indexes:
            indexes.sort(reverse=True)
            for i in indexes:
                mainStr = mainStr[:i] + mainStr[i+1:]
            return mainStr
        return False

    def originalDigits(self, s):
        r = [0] * 10

        for o in self.order:
           while self.indexOf(s, self.text[o]):
               s = self.remove(s, self.text[o])
               r[o] += 1

        a = ""
        for i in range(len(r)):
            for j in range(r[i]):
                a += str(i)
        return a

print(Solution().originalDigits("zeroonetwothreefourfivesixseveneightnine"))