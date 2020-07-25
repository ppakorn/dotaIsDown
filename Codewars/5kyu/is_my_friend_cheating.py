def removNb(n):
    sum = n * (n+1) / 2
    ans = []
    for x in range(int(n / 2), n):
        ex_sum = sum - x
        if ex_sum % (x + 1) == 0:
            y = int(ex_sum / (x + 1))
            ans.append((x, y))
    return ans


print(removNb(26))
