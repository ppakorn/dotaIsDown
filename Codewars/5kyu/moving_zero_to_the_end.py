def move_zeros(array):
    new_array = list(filter(lambda x: (x != 0) or (x is False), array))
    num_zero = len(array) - len(new_array)
    for i in range(0, num_zero):
        new_array.append(0)
    return new_array


print(move_zeros([9, 0.0, 0, 9, 1, 2, 0, 1, 0, 1, 0.0, 3, 0, 1, 9, 0, 0, 0, 0, 9]))
print(move_zeros([0, 1, None, 2, False, 1, 0]))

print(False is not 0)
print(0.0 is not False)
print(0.0 is not 0)
print(0 is not 0.0)
print(0.0 is not 0.0)
print(0.0 != 0)
