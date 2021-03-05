package util

fun Int.toBinary(len: Int): String {
    return String.format("%" + len + "s", this.toString(2)).replace(" ".toRegex(), "0")
}

fun Int.ithBit(i: Int): Boolean {
    return (this shr i) and 1 == 1
}