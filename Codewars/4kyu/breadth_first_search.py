class Node:
    def __init__(self, L, R, n):
        self.left = L
        self.right = R
        self.value = n


def tree_by_levels(node):
    if not node:
        return []

    q = [node]
    i = 0
    while i < len(q):
        n = q[i]
        i += 1

        if n.left:
            q.append(n.left)
        if n.right:
            q.append(n.right)
    return [x.value for x in q]


print(tree_by_levels(Node(Node(None, Node(None, None, 4), 2), Node(Node(None, None, 5), Node(None, None, 6), 3), 1)))
