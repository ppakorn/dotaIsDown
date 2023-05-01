def find_even_index(arr):
    sum_ = 0
    left = []
    for i in arr:
        left.append(sum_)
        sum_ += i

    sum_ = 0
    l = len(arr)
    right = [None] * l
    for i, v in enumerate(reversed(arr)):
        right[l - i - 1] = sum_
        sum_ += v

    for i in range(0, l):
        if left[i] == right[i]:
            return i

    return -1
