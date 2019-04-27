n = int(input())

if n == 0:
	print('10')
	exit()
if n == 1:
	print('1')
	exit()

list = [0] * 10
currentDivider = 9

while currentDivider > 1:
	if n % currentDivider == 0:
		list[currentDivider] += 1
		n /= currentDivider
	else:
		currentDivider -= 1

if n == 1:
	for i in range(2, len(list)):
		for k in range(0, list[i]):
			print(i, end='')
	print()
else:
	print('-1')
