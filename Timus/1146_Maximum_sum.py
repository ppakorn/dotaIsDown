def verticalSum(arr):
    n = len(arr)
    parSum = [[0] * n for i in range(n)]

    for j in range(n):
        sum = 0
        for i in range(n):
            sum += arr[i][j]
            parSum[i][j] = sum

    return parSum

# from 1296
def hyperjump(a) :
    max = -128
    sum = 0

    for i in range(len(a)):
        if sum > 0:
            sum += a[i]
        else:
            sum = a[i]

        if sum > max:
            max = sum

    return max

def partialSum(vSum, h, p):
    n = len(vSum)
    parSum = [0] * n

    for j in range(n):
        if p == 0:
            parSum[j] = vSum[p+h][j]
        else:
            parSum[j] = vSum[p+h][j] - vSum[p-1][j]

    return parSum

def maxSum(arr):
    vSum = verticalSum(arr)
    max = -128

    # height
    # h = 0 means 1 row, h = 1 means 2 rows
    for h in range(n):
        # position of i
        for p in range(0, n-h):
            parSum = partialSum(vSum, h, p)
            parMax = hyperjump(parSum)
            if parMax > max:
                max = parMax

    return max

n = int(input())
a = []
for i in range(n):
    inp = list(map(int, input().split()))
    a.append(inp)

print(maxSum(a))