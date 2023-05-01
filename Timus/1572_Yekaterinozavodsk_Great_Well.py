from math import sqrt

type, size = map(int, input().split())

if type == 1:
    wellSize = 2 * size
elif type == 2:
    wellSize = sqrt(2) * size
else:
    wellSize = size

def coverSize(type, size):
    if type == 1:
        return 2 * size
    elif type == 2:
        return size
    else:
        return sqrt(3) * size / 2

n = int(input())
count = 0
for i in range(n):
    ct, cs = map(int, input().split())
    if coverSize(ct, cs) <= wellSize:
        count += 1

print(count)