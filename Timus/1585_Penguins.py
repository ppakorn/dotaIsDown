n = int(input())

emperor = 0
macaroni = 0
little = 0

for i in range(n):
    s = input()
    if s == 'Emperor Penguin':
        emperor += 1
    elif s == 'Macaroni Penguin':
        macaroni += 1
    elif s == 'Little Penguin':
        little += 1

max = max(emperor, macaroni, little)
if max == emperor:
    print('Emperor Penguin')
elif max == macaroni:
    print('Macaroni Penguin')
else:
    print('Little Penguin')