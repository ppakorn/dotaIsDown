def find_it(seq):
    a = 0
    for i in seq:
        a = a ^ i
    return a
