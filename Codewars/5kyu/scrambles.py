def make_char_freq(s):
    char_pool = {}
    for i in s:
        if i in char_pool:
            char_pool[i] += 1
        else:
            char_pool[i] = 1
    return char_pool


def scramble(s1, s2):
    char_pool = make_char_freq(s1)
    char_needed = make_char_freq(s2)

    for char in char_needed:
        num_needed = char_needed[char]
        if char not in char_pool or char_pool[char] < num_needed:
            return False

    return True


print(scramble('rkqodlw', 'world'))
print(scramble('cedewaraaossoqqyt', 'codewars'))
print(scramble('katas', 'steak'))
print(scramble('scriptjava', 'javascript'))
print(scramble('scriptingjava', 'javascript'))
