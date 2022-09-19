# you can write to stdout for debugging purposes, e.g.
# print "this is a debug message"

# knapsack algorithm
def solution(A):
    # write your code in Python 2.7

    total = sum(A)
    max = 0
    bound = int((total / 2 + 1))
    dict = {}

    for a in A:
        if a >= bound:
            continue

        new = []
        for key in dict:
            if key + a < bound:
                new.append(key + a)
                if key + a > max:
                    max = key + a

        for key in new:
            if not key in dict:
                dict[key] = True

        if not a in dict:
            dict[a] = True
            if a > max:
                max = a

    return abs(total - max - max)

n = int(input())
A = list(map(int, input().split()))

print(solution(A))

