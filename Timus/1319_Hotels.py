n = int(input())

a = [[0] * n for i in range(n)]

c = 0
i, j = 0, n-1-c
phase2 = False
for x in range(1, n**2+1):
    a[i][j] = x

    if j+1 < n and i+1 < n:
        i += 1
        j += 1
        if i == n-1 and j == n-1:
            phase2 = True
            c = 0
    elif not phase2:
        i = 0
        c += 1
        j = n-1-c
    else:
        c += 1
        i = c
        j = 0

for i in range(n):
    for j in range(n):
        print(str(a[i][j]) + ' ', end='')
    print()