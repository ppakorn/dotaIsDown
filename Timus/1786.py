str = input()
sandro = ['D', 'o', 'n', 'a', 'l', 'd']

def calCost(char, expectedChar):
	if char == expectedChar:
		return 0

	if (
		(char.islower() and expectedChar.islower()) or
		(char.isupper() and expectedChar.isupper()) or
		(char.lower() == expectedChar.lower())
		):
		return 5

	return 10

min = 100
for i in range(0, len(str) - len(sandro) + 1):
	cost = 0
	for j in range(0, len(sandro)):
		cost += calCost(str[i+j], sandro[j])

	if cost < min:
		min = cost

print(min)

