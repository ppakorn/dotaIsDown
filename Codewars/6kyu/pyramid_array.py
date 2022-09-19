def pyramid(n):
    ans = []
    for i in range(1, n + 1):
        a = [1] * i
        ans.append(a)
    return ans


print(pyramid(0))
print(pyramid(1))
print(pyramid(2))
print(pyramid(5))
