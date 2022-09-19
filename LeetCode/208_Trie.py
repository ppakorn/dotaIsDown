class Node:
    def __init__(self):
        self.children = [None] * 26
        self.isLeaf = False


class Trie(object):
    def __init__(self):
        """
        Initialize your data structure here.
        """
        self.root = Node()

    def charToIndex(self, char):
        return ord(char) - ord('a')

    def insert(self, word):
        """
        Inserts a word into the trie.
        :type word: str
        :rtype: void
        """
        current = self.root
        l = len(word)
        for i in range(l):
            index = self.charToIndex(word[i])
            if not current.children[index]:
                current.children[index] = Node()
            current = current.children[index]

        current.isLeaf = True

    def search(self, word):
        """
        Returns if the word is in the trie.
        :type word: str
        :rtype: bool
        """
        current = self.root
        l = len(word)
        for i in range(l):
            index = self.charToIndex(word[i])
            if not current.children[index]:
                return False
            current = current.children[index]

        return current.isLeaf

    def startsWith(self, prefix):
        """
        Returns if there is any word in the trie that starts with the given prefix.
        :type prefix: str
        :rtype: bool
        """
        current = self.root
        l = len(prefix)
        for i in range(l):
            index = self.charToIndex(prefix[i])
            if not current.children[index]:
                return False
            current = current.children[index]

        return True



# Your Trie object will be instantiated and called as such:
obj = Trie()
obj.insert('word')
obj.insert('abbc')
print(obj.search('word'))
print(obj.search('abc'))
print(obj.search('abb'))
print(obj.search('abbc'))
print(obj.startsWith('w'))
print(obj.startsWith('ab'))
print(obj.startsWith('abd'))
print(obj.startsWith('abbc'))
# param_2 = obj.search(word)
# param_3 = obj.startsWith(prefix)