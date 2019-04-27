post = [['A', 'P', 'O', 'R'], ['B', 'M', 'S'], []]

count = 0
current = 0

n = int(input())

for i in range(n):
    receiver = input()
    r = receiver[0]

    if r in post[0]:
        target = 0
    elif r in post[1]:
        target = 1
    else:
        target = 2

    count += abs((target - current))
    current = target

print(count)