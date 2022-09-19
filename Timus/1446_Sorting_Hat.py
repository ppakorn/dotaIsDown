n = int(input())

sorting = {
    'S': [],
    'H': [],
    'G': [],
    'R': []
}

for i in range(n):
    name = input()
    house = input()
    h = house[:1]
    sorting[h].append(name)

print('Slytherin:')
for name in sorting['S']:
    print(name)
print()

print('Hufflepuff:')
for name in sorting['H']:
    print(name)
print()

print('Gryffindor:')
for name in sorting['G']:
    print(name)
print()

print('Ravenclaw:')
for name in sorting['R']:
    print(name)
print()