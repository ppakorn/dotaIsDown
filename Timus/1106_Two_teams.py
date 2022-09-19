n = int(input())

process = [False] * (n+1)
a = [[0]]

for i in range(1, n+1):
    temp = [int(x) for x in input().split() if x]
    a.append(sorted(temp[:-1]))

team1, team2 = [], []
process[1] = True
stack = [(1, 0)]

while len(stack) > 0:
    player, team = stack.pop(0)

    if team == 0:
        team1.append(player)
    else:
        team2.append(player)

    frTeam = (team + 1) % 2
    for p in reversed(a[player]):
        if not process[p]:
            stack.insert(0, (p,frTeam))
            process[p] = True

    if len(stack) == 0 and (len(team1) + len(team2) < n):
        newP = max(max(team1), max(team2)) + 1
        stack.insert(0, (newP, 0))
        process[newP] = True

print(len(team1))
for p in team1:
    print(str(p) + ' ', end='')