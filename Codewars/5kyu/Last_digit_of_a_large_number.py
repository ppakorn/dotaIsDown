# key = last digit of n1
# value = list of last digit after power (it must be a loop)
last_digit_dict = {
    0: [0],
    1: [1],
    2: [2, 4, 8, 6],
    3: [3, 9, 7, 1],
    4: [4, 6],
    5: [5],
    6: [6],
    7: [7, 9, 3, 1],
    8: [8, 4, 2, 6],
    9: [9, 1]
}


def last_digit(n1, n2):
    if n2 == 0:
        return 1
    if n1 == 0:
        return 0

    list1 = [int(d) for d in str(n1)]
    last1 = list1[-1]

    loop = last_digit_dict[last1]
    index = n2 % len(loop) - 1

    return loop[index]
