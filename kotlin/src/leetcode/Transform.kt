package leetcode

fun transform2DIntArray(str: String): Array<IntArray> {
    return str
        .removePrefix("[")
        .removeSuffix("]")
        .split("],")
        .map { a ->
            val aa = a.removePrefix("[").removeSuffix("]")
            if (aa.isEmpty()) {
                emptyList<Int>().toIntArray()
            } else {
                aa.split(",").map { it.toInt() }.toIntArray()
            }
        }
        .toTypedArray()
}

fun transform2DListInt(str: String): List<List<Int>> {
    return str
        .removePrefix("[")
        .removeSuffix("]")
        .split("],")
        .map { a ->
            val aa = a.removePrefix("[").removeSuffix("]")
            if (aa.isEmpty()) {
                emptyList<Int>()
            } else {
                aa.split(",").map { it.toInt() }
            }
        }
}


fun transform2DString(str: String): Array<String> {
    return str
        .removePrefix("[")
        .removeSuffix("]")
        .replace("\n", "")
        .replace("\"", "")
        .split("],")
        .map { a ->
            val aa = a.removePrefix("[").removeSuffix("]")
            if (aa.isEmpty()) {
                ""
            } else {
                aa.split(",").joinToString("")
            }
        }
        .toTypedArray()
}

fun transform2DCharArray(str: String): Array<CharArray> {
    return transform2DString(str).map { it.toCharArray() }.toTypedArray()
}
