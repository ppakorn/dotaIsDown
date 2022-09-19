a, b = map(int, input().split())

if b >= a:
    print(b*2 + 40)
else:
    print(39*2 + 40 + (a-40)*2 + 1)