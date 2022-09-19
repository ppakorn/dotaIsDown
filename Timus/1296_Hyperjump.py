def solution(a) :
    max = 0
    sum = 0

    for i in range(len(a)):
        if sum > 0:
            sum += a[i]
        else:
            sum = a[i]

        if sum > max:
            max = sum

    return max

n = int(input())
a = [0] * n
for i in range(n):
    a[i] = int(input())

print(solution(a))