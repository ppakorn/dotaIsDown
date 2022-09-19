# from math import sqrt
#
# sq = [1]
# result = [0]
#
# n = int(input())
#
# if len(result) > n:
#     print(result[n])
#     exit()
#
# def isSq(k):
#     return int(sqrt(k)) == sqrt(k)
#
# for i in range(len(result), n+1):
#     if isSq(i):
#         result.append(1)
#         sq.append(i)
#         continue
#
#     min = 70000
#     for j in range(0, len(sq)):
#         if sq[j] > i / 2:
#             break
#         r = 1 + result[i-sq[j]]
#         if r < min:
#             min = r
#             if min == 2:
#                 break
#     result.append(min)
#
# print(result[n])

from math import sqrt, floor

done = [False] * 60000
n = int(input())

def isSq(k):
    return int(sqrt(k)) == sqrt(k)

queue = [(0, n)]

min = 99999
while len(queue) > 0:
    p, q = queue.pop(0)

    if isSq(q):
        print(p + 1)
        exit()

    max = int(floor(sqrt(q))) + 1
    min = int(floor(sqrt(q / 4)))
    for i in reversed(range(min, max)):
        k = q - i**2
        if not done[k]:
            queue.append((p+1, q - i**2))
            done[k] = True
