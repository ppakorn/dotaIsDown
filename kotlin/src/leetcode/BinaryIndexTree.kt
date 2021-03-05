class BinaryIndexTree(
    val size: Int
) {
    private val bit = IntArray(size)

    fun getSum(index: Int): Int {
        var i = index
        var sum = 0
        while (i > 0) {
            sum += bit[i]
            i -= (i and -i)
        }
        return sum
    }

    fun update(index: Int, incValue: Int) {
        var i = index
        while (i < size) {
            bit[i] += incValue
            i += (i and -i)
        }
    }
}