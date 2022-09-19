n = int(input())

if n % 4 == 0:
    print(0)
elif n % 4 == 2:
    print(1)
elif n % 4 == 3:
    print(2)
else:
    if n % 20 == 5:
        print(2)
    else:
        print(1)
