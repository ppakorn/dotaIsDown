n = int(input())

val = [0] * n
sum = [0] * n
dup = [-1] * n

ans = -1
anssum = -1
val[0] = int(input())
sum[0] = val[0] % n

if sum[0] == 0:
    ans = val[0]

for i in range(1, n):
    val[i] = int(input())
    sum[i] = (sum[i-1] + val[i]) % n

    if val[i] % n == 0:
        ans = val[i]

    if sum[i] == 0:
        anssum = i

if ans != -1:
    print(1)
    print(ans)
    exit()

if anssum != -1:
    print(anssum + 1)
    for i in range(anssum+1):
        print(val[i])
    exit()

for i in range(n):
    if dup[sum[i]] == -1:
        dup[sum[i]] = i
    else:
        s1 = dup[sum[i]]
        s2 = i
        break

print(s2-s1)
for i in range(s1+1, s2+1):
    print(val[i])