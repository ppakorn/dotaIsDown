class Solution278: VersionControl() {
    override fun firstBadVersion(n: Int) : Int {
        var l = 1
        var r = n
        while (l < r) {
            val mid = l + (r - l) / 2
            if (isBadVersion(mid)) {
                r = mid
            } else {
                l = mid + 1
            }
        }
        return l
    }
}

open class VersionControl {
    fun isBadVersion(version: Int): Boolean {
        return version >= 5
    }

    open fun firstBadVersion(n: Int): Int {
        return 0
    }
}