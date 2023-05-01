def choose_best_sum(t, k, ls):
    ls = list(filter(lambda x: x <= t, ls))

    if k == 1:
        return max(ls)

    mem = []
    best = -1

    for l in ls:
        new_mem = []
        for (time, distance) in mem:
            new_time = time + 1
            new_distance = distance + l

            if new_time == k and best < new_distance <= t:
                best = new_distance

            if new_time < k:
                new_mem.append((new_time, new_distance))

        mem.extend(new_mem)
        mem.append((1, l))

    if best == -1:
        return None
    return best

