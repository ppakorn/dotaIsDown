class Solution(object):
    def trap(self, height):
        l = 0
        width = 0
        count = 0
        sumAlongWay = 0

        lastL = -1

        for i in range(len(height)):
            h = height[i]
            if h >= l:
                count += width * l - sumAlongWay
                l = h
                width = 0
                sumAlongWay = 0
                lastL = i
                continue

            sumAlongWay += h
            width += 1

        if lastL == -1:
            return count

        r = 0
        width = 0
        sumAlongWay = 0
        for i in reversed(range(lastL, len(height))):
            h = height[i]
            if h >= r:
                count += width * r - sumAlongWay
                r = h
                width = 0
                sumAlongWay = 0
                continue

            sumAlongWay += h
            width += 1

        return count



Solution().trap([0,1,0,2,1,0,1,3,2,1,2,1])