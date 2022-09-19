def sum_dig_pow(a, b): # range(a, b + 1) will be studied by the function

    ans = []

    for i in range(a, b + 1):
        s = str(i)
        value = 0
        for j in range(0, len(s)):
            value += int(s[j]) ** (j + 1)

        if value == i:
            ans.append(i)

    return ans
