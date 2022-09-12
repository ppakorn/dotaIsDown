def to_string(factor):
    s = ""
    for k in factor:
        s += '('
        if factor[k] > 1:
            s += f"{k}**{factor[k]}"
        else:
            s += f"{k}"
        s += ')'

    return s


def prime_factors(n):
    factor = {}
    while n % 2 == 0:
        factor[2] = factor.get(2, 0) + 1
        n //= 2

    i = 3
    while n > 1:
        while n % i == 0:
            factor[i] = factor.get(i, 0) + 1
            n //= i
        i += 2

    return to_string(factor)


print(prime_factors(7775460))
