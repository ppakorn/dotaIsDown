
import kotlin.collections.*


/*
 * Complete the 'itemsSort' function below.
 *
 * The function is expected to return an INTEGER_ARRAY.
 * The function accepts INTEGER_ARRAY items as parameter.
 */

fun itemsSort(items: Array<Int>): Array<Int> {
    // Example items = [4, 5, 6, 5, 4, 3]

    // Collect frequency of each element
    // frequency = {3: 1, 4: 2, 5: 2, 6: 1}
    val frequency = mutableMapOf<Int, Int>()
    items.forEach {
        frequency[it] = (frequency[it] ?: 0) + 1
    }

    val result = items.sortedWith(compareBy({ frequency[it] }, { it }))
    return result.toTypedArray()

//    // Categorize by frequency
//    // repeat = {1: [3, 6], 2: [5, 4]}
//    val repeat = mutableMapOf<Int, MutableList<Int>>()
//    frequency.forEach { (item, times) ->
//        if (repeat[times] == null) {
//            repeat[times] = mutableListOf()
//        }
//        repeat[times]!!.add(item)
//    }
//
//    // Build result array
//    val result = mutableListOf<Int>()
//    repeat.keys.sorted().forEach { times ->
//        val sortedItems = repeat[times]?.sorted()
//        sortedItems?.forEach {
//            for (i in 0 until times) {
//                result.add(it)
//            }
//        }
//    }
//
//    return result.toTypedArray()
}

fun main(args: Array<String>) {
//    val itemsCount = readLine()!!.trim().toInt()
//
//    val items = Array<Int>(itemsCount, { 0 })
//    for (i in 0 until itemsCount) {
//        val itemsItem = readLine()!!.trim().toInt()
//        items[i] = itemsItem
//    }
//
//    val result = itemsSort(items)
//
//    println(result.joinToString("\n"))

    println(itemsSort(arrayOf(3, 3, 5, 1, 2, 5, 4)).contentToString())
}
