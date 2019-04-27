def knight(s):
    x = ord(s[0]) - ord('a')
    y = ord(s[1]) - ord('1')
    count = 0

    # up
    if y >= 2:
        if x == 0 or x == 7:
            count += 1
        else:
            count += 2

    # down
    if y <= 5:
        if x == 0 or x == 7:
            count += 1
        else:
            count += 2

    # left
    if x >= 2:
        if y == 0 or y == 7:
            count += 1
        else:
            count += 2

    # right
    if x <= 5:
        if y == 0 or y == 7:
            count += 1
        else:
            count += 2

    return count


n = int(input())

for i in range(n):
    k = input()
    print(knight(k))