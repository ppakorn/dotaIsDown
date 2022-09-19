def make_floor(floor, n): # floor start with 0
    s = ''
    space = n - (floor + 1)
    for i in range(0, space):
        s += ' '
    for i in range(0, floor * 2 + 1):
        s += '*'
    for i in range(0, space):
        s += ' '
    return s


def tower_builder(n_floors):
    result = []
    for i in range(n_floors):
        result.append(make_floor(i, n_floors))
    return result


print(tower_builder(1))
print(tower_builder(3))
print(tower_builder(5))