def same_structure_as(original, other):
    if not isinstance(original, list) or not isinstance(other, list):
        return type(original) == type(other)

    if len(original) != len(other):
        return False

    for i in range(len(original)):
        if isinstance(original[i], list):
            if isinstance(other[i], list):
                if not same_structure_as(original[i], other[i]):
                    return False
            else:
                return False
        elif isinstance(other[i], list):
            return False

    return True


# should return True
print(same_structure_as([1, 1, 1], [2, 2, 2]))
print(same_structure_as([1, [1, 1]], [2, [2, 2]]))

# should return False
print(same_structure_as([1, [1, 1]], [[2, 2], 2]))
print(same_structure_as([1, [1, 1]], [[2], 2]))

# should return True
print(same_structure_as([[[], []]], [[[], []]]))

# should return False
print(same_structure_as([[[], []]], [[1, 1]]))

print(same_structure_as(1, 'a'))