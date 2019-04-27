k = int(input())

g = list(map(int, input().split()))

g.sort()
half = int(len(g) / 2) + 1
count = 0

for i in range(0, half):
	h = int(g[i] / 2) + 1
	count += h

print(count)
