def next_bigger(n):
    n_digit = [int(c) for c in str(n)]
    left, x, right_digit = find_x(n_digit)

    if x is None:
        return -1

    middle = find_next_bigger(x, right_digit)
    right_digit[middle] -= 1
    right_digit[x] += 1
    right = sort_right(right_digit)

    l = left + [middle] + right
    s = [str(i) for i in l]
    return int("".join(s))


def sort_right(right_digit):
    right = []
    for i, count in enumerate(right_digit):
        right += [i] * count
    return right


def find_next_bigger(x, digit_count):
    for i in range(x + 1, len(digit_count)):
        if digit_count[i] > 0:
            return i
    raise Exception(f"find_next_bigger has something wrong x={x}, digit_count={digit_count}")


def find_x(n_digit):
    digit_count = [0] * 10
    previous = None
    for i in range(len(n_digit) - 1, -1, -1):
        x = n_digit[i]

        # Find digit that can be replaced = lower than max of what we found from right to left
        if previous is None or x >= previous:
            digit_count[x] += 1
            previous = x
        else:
            return n_digit[:i], x, digit_count

    return [], None, []


# print(next_bigger(12) == 21)
# print(next_bigger(513) == 531)
# print(next_bigger(2017) == 2071)
# print(next_bigger(414) == 441)
# print(next_bigger(144) == 414)
# print(next_bigger(13987) == 17389)
# print(next_bigger(17983) == 18379)
# print(next_bigger(1983) == 3189)
# print(next_bigger(189763) == 193678)
# print(next_bigger(9876543210) == -1)
print(next_bigger(344090))