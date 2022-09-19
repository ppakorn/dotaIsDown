class Solution(object):
    order = [0, 2, 4, 6, 8, 5, 7, 3, 1, 9]
    text = ["zero", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine"]

    def originalDigits(self, s):
        r = { 'z': 0,
              'e': 0,
              'r': 0,
              'o': 0,
              'n': 0,
              't': 0,
              'w': 0,
              'h': 0,
              'f': 0,
              'u': 0,
              'i': 0,
              'v': 0,
              's': 0,
              'x': 0,
              'g': 0, }

        for c in s:
            r[c] += 1

        n = [0] * 10

        n[0] = r['z']
        n[2] = r['w']
        n[4] = r['u']
        n[6] = r['x']
        n[8] = r['g']
        n[5] = r['f'] - n[4]
        n[7] = r['v'] - n[5]
        n[3] = r['h'] - n[8]
        n[1] = r['o'] - n[0] - n[2] - n[4]
        n[9] = r['i'] - n[5] - n[6] - n[8]

        a = ""
        for i in range(len(n)):
            a += str(i) * n[i]
        return a

Solution().originalDigits("zeroonetwothreefourfivesixseveneightnine")