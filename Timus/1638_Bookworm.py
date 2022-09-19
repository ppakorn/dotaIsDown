thickness, coverThick, start, stop = map(int, input().split())

if start < stop:
    print(coverThick * 2 + (thickness + 2*coverThick) * (stop - start - 1))
elif start == stop:
    print(thickness)
else:
    print((thickness + 2 * coverThick) * (start - stop + 1) - coverThick * 2)