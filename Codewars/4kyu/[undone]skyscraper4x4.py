def solve_puzzle(clues):
    table = [[-1, -1, -1, -1], [-1, -1, -1, -1], [-1, -1, -1, -1], [-1, -1, -1, -1]]
    rc = reorder_clue(clues)
    print(rc)
    start0(table, clues)
    # insert_for_4(table, clues)
    # insert_for_1(table, clues)

    sets = create_sets()
    answer = recursive(table, rc, sets)

    # Make list to tuple
    return answer


def start0(table, clues):
    for idx, clue in enumerate(clues):
        if idx < 4 and clue > 0:
            table[0][idx] = 0
            table[1][idx] = 0
            table[2][idx] = 0
            table[3][idx] = 0
        elif idx < 8 and clue > 0:
            table[idx % 4][0] = 0
            table[idx % 4][1] = 0
            table[idx % 4][2] = 0
            table[idx % 4][3] = 0
        elif idx < 12 and clue > 0:
            table[0][3 - idx % 4] = 0
            table[1][3 - idx % 4] = 0
            table[2][3 - idx % 4] = 0
            table[3][3 - idx % 4] = 0
        elif clue > 0:
            table[3 - idx % 4][0] = 0
            table[3 - idx % 4][1] = 0
            table[3 - idx % 4][2] = 0
            table[3 - idx % 4][3] = 0


# def insert_for_4(table, clues):
#     for idx, clue in enumerate(clues):
#         if idx < 4 and clue == 4:
#             table[0][idx] = 1
#             table[1][idx] = 2
#             table[2][idx] = 3
#             table[3][idx] = 4
#         elif idx < 8 and clue == 4:
#             table[idx % 4][0] = 4
#             table[idx % 4][1] = 3
#             table[idx % 4][2] = 2
#             table[idx % 4][3] = 1
#         elif idx < 12 and clue == 4:
#             table[0][3 - idx % 4] = 4
#             table[1][3 - idx % 4] = 3
#             table[2][3 - idx % 4] = 2
#             table[3][3 - idx % 4] = 1
#         elif clue == 4:
#             table[3 - idx % 4][0] = 1
#             table[3 - idx % 4][1] = 2
#             table[3 - idx % 4][2] = 3
#             table[3 - idx % 4][3] = 4
#
#
# def insert_for_1(table, clues):
#     for idx, clue in enumerate(clues):
#         if idx < 4 and clue == 1:
#             table[0][idx] = 4
#         elif idx < 8 and clue == 1:
#             table[idx % 4][3] = 4
#         elif idx < 12 and clue == 1:
#             table[3][3 - idx % 4] = 4
#         elif clue == 1:
#             table[3 - idx % 4][0] = 4


def create_sets():
    set4 = [[1, 2, 3, 4]]
    set1 = [
        [4, 1, 2, 3],
        [4, 1, 3, 2],
        [4, 2, 3, 1],
        [4, 2, 1, 3],
        [4, 3, 1, 2],
        [4, 3, 2, 1],
    ]
    set2 = [
        [1, 4, 2, 3],
        [1, 4, 3, 2],
        [2, 1, 4, 3],
        [2, 4, 1, 3],
        [2, 4, 3, 1],
        [3, 1, 2, 4],
        [3, 1, 4, 2],
        [3, 2, 1, 4],
        [3, 2, 4, 1],
        [3, 4, 1, 2],
        [3, 4, 2, 1],
    ]
    set3 = [
        [1, 2, 4, 3],
        [1, 3, 2, 4],
        [1, 3, 4, 2],
        [2, 1, 3, 4],
        [2, 3, 1, 4],
        [2, 3, 4, 1],
    ]
    return [[], set1, set2, set3, set4]

def reorder_clue(clues):
    order = [4, 1, 3, 2]
    reorder = []
    for o in order:
        for idx, clue in enumerate(clues):
            if clue == o:
                reorder.append((idx, clue))
    return reorder


def recursive(table, rc, sets):
    count0 = sum(row.count(0) for row in table)
    if count0 == 0:
        return table

    

# t = solve_puzzle(( 0, 0, 1, 2,
#   0, 2, 0, 0,
#   0, 3, 0, 0,
#   0, 1, 0, 0 ))
t = solve_puzzle(( 2, 2, 1, 3,
  2, 2, 3, 1,
  1, 2, 2, 3,
  3, 2, 1, 3 ))
print(t)
