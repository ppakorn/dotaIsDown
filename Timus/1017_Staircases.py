n = int(input())

# def cal(a, k):
#     return int((k-1) / 2) - a
#
# count = cal(0, n)
# initial = (0, n)
# queue = [(initial)]
#
# while len(queue) > 0:
#     print(queue)
#     a, k = queue.pop(0)
#     for i in range(a+1, k):
#         x = k - i
#
#         c = cal(i, x)
#         if c > 0:
#             count += c
#             queue.append((i, x))
#         else:
#             break
#
# print(count)

a = [[0] * (n+1) for i in range(n+1)]

a[3][1] = 1
a[4][1] = 1

for i in range(5, len(a)):
    f = int((i-1) / 2)
    a[i][f] = 1
    for j in reversed(range(1, f)):
        a[i][j] = a[i][j+1] + a[i-j][j+1] + 1


print(a[n][1])