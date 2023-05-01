# def sumEachDigit(num, maxDigit):
# 	sum = 0
# 	for i in reversed(range(0, maxDigit)):
# 		r = int(num / pow(10, i))
# 		sum += r
# 		num -= r * pow(10, i)
#
# 	return sum
#
# def sumArray(digit):
# 	sumArr = [0] * (9 * digit + 1)
# 	for i in range(0, pow(10, digit)):
# 		sum = 0
# 		for j in reversed((0, digit)):
# 			sum = sumEachDigit(i, digit)
# 		sumArr[sum] += 1
#
# 	return sumArr
#
# print(sumArray(5))

arr = [[1],
[1, 1, 1, 1, 1, 1, 1, 1, 1, 1],
[1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1],
[1, 3, 6, 10, 15, 21, 28, 36, 45, 55, 63, 69, 73, 75, 75, 73, 69, 63, 55, 45, 36, 28, 21, 15, 10, 6, 3, 1],
[1, 4, 10, 20, 35, 56, 84, 120, 165, 220, 282, 348, 415, 480, 540, 592, 633, 660, 670, 660, 633, 592, 540, 480, 415, 348, 282, 220, 165, 120, 84, 56, 35, 20, 10, 4, 1]]

arr2 = []
for i in range(0, len(arr)):
	arr2.append(list(map((lambda x: x **2), arr[i])))

ans = []
for i in range(0, len(arr2)):
	sum = 0
	for j in range(0, len(arr2[i])):
		sum += arr2[i][j]
	ans.append(sum)

a = int(input())
print(ans[int(a/2)])
