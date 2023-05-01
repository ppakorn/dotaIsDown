from math import log
from math import isclose


def isPP(n):
    i = 2
    while True:
        a = log(n, i)
        if isclose(a, int(a), rel_tol=1e-9, abs_tol=0.0):
            return [i, int(a)]

        if isclose(a, int(a) + 1, rel_tol=1e-9, abs_tol=0.0):
            return [i, int(a) + 1]

        if a < 2:
            return None

        i += 1

print(isPP(140608))
