def add(stack, x, c):
    if x == '(' and c == ']':
        if stack.count('[') > stack.count('(') :
            return ')'
        return '['
    if x == '[' and c == ')':
        if stack.count('(') > stack.count('['):
            return ']'
        return '('

def brackets(a):
    stack = []
    current = 0
    while current < len(a):
        c = a[current]
        if c == '(' or c == '[':
            stack.append(c)
            current += 1
        else:
            if len(stack) == 0:
                if c == ')':
                    a = '(' + a
                elif c == ']':
                    a = '[' + a
                current += 2
            else:
                x = stack.pop()
                if (x == '(' and c == ')') or (x == '[' and c == ']'):
                    current += 1
                    continue

                ad = add(stack, x, c)
                a = a[:current] + ad + a[current:]
                if ad == '[' or ad == '(':
                    stack.append(x)
                    stack.append(ad)
                current += 1

    while len(stack) > 0:
        x = stack.pop()
        if x == '(':
            a = a + ')'
        elif x == '[':
            a = a + ']'

    return a

a = input()
print(brackets(a))