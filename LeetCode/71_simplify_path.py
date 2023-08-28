class Solution71(object):
    def simplifyPath(self, path):
        """
        :type path: str
        :rtype: str
        """
        paths = path.split("/")
        stack = []

        for p in paths:
            if p == "..":
                if stack:
                    stack.pop()
            elif len(p) == 0 or p == ".":
                continue
            else:
                stack.append(p)

        result = "/".join(stack)
        result = "/" + result

        return result


s = Solution71()
print(s.simplifyPath("/a/./b/../../c/"))
