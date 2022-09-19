class Solution406 {

    fun reconstructQueue(people: Array<IntArray>): Array<IntArray> {
        return thirdEdition(people)
    }

    // 1. sort by h ascending then k descending
    // 2. insert one by one
    // idea is considering the empty slot will be filled with higher h later
    private fun thirdEdition(people: Array<IntArray>): Array<IntArray> {
        val cmp = compareBy<IntArray> { it[0] }.thenByDescending { it[1] }
        val sortedPeople = people.sortedArrayWith(cmp)

        val dummyIntArray = intArrayOf()
        val result = Array(people.size) { dummyIntArray }
        sortedPeople.forEach {
            var k = it[1]
            var i = 0
            while (k > 0) {
                if (result[i].isEmpty()) {
                    k -= 1
                }
                i += 1
            }
            while (result[i].isNotEmpty()) {
                i += 1
            }
            result[i] = it
        }

        return result
    }

    // 1. sort by h descending then k ascending
    // 2. insert one by one
    private fun secondEdition(people: Array<IntArray>): Array<IntArray> {
        val hComparator = compareByDescending<IntArray> { it[0] }
        val hThenKComparator = hComparator.thenBy { it[1] }
        val sortedPeople = people.sortedArrayWith(hThenKComparator)

//        sortedPeople.forEach {
//            println(it.contentToString())
//        }

        val result = mutableListOf<IntArray>()
        sortedPeople.forEach {
            var k = it[1]
            var i = 0
            while (k > 0 && result[i].isNotEmpty()) {
                if (result[i][0] >= it[0]) {
                    k -= 1
                }
                i += 1
            }
            result.add(i, it)
        }

        return result.toTypedArray()
    }


    // 1. find min that has k=0
    // 2. push in the answer array
    // 3. update k for all the remaining (k -= 1 for h that < h from 2)
    private fun firstEdition(people: Array<IntArray>): Array<IntArray> {
        val dummyIntArray = intArrayOf()
        val result = Array(people.size) { dummyIntArray }

        people.forEachIndexed { i, p ->
            val newp = intArrayOf(p[0], p[1], p[1])
            people[i] = newp
        }

        people.indices.forEach { i ->
            val min = findNextInQueue(people)
            result[i] = intArrayOf(min[0], min[1])
            updateQueue(people, min[0])
        }

        result.forEach {
            println(it.contentToString())
        }

        return result
    }

    private fun findNextInQueue(people: Array<IntArray>): IntArray {
        var min = intArrayOf(Int.MAX_VALUE, 0, Int.MAX_VALUE)
        people.forEach {
            if (it[2] == 0 && it[0] < min[0]) {
                min = it
            }
        }
        return min
    }

    private fun updateQueue(people: Array<IntArray>, height: Int) {
        people.forEach {
            if (it[0] <= height) {
                it[2] -= 1
            }
        }
    }
}

fun main() {
    val s = Solution406()
    val case1 = s.reconstructQueue(arrayOf(
        intArrayOf(7, 0),
        intArrayOf(4, 4),
        intArrayOf(5, 0),
        intArrayOf(6, 1),
        intArrayOf(7, 1),
        intArrayOf(5, 2)
    ))
    case1.forEach {
        print(it.contentToString())
        print(", ")
    }
    println()
}