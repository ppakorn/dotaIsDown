def digital_root(n):
    if n < 10:
        return n

    s = str(n)
    total = sum(map(lambda x: int(x), s))
    return digital_root(total)
