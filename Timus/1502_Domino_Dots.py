def sumTo(n):
    return int((n) * (n+1) / 2)

def dominoDots(n):
    return (n+2) * sumTo(n)

n = int(input())
print(dominoDots(n))