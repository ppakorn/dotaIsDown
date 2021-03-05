package advent2020

class FindSeaMonsters(
    private var raw: List<String>
) {
    private val mark = Array(raw.size) { Array(raw[0].length) { false } }

    fun execute(): Int {
        for (i in 0 until 4) {
            if (find())
                return findSeaLevel()
            rotateR()
        }

        flip()

        for (i in 0 until 4) {
            if (find())
                return findSeaLevel()
            rotateR()
        }

        throw Exception("Not found in any orientation")
    }

    private fun find(): Boolean {
        val body = Regex("#.{4}##.{4}##.{4}###")
        var found = false

        for (rowIndex in 1 until raw.size - 1) {
            val row = raw[rowIndex]
            val matchResults = body.findAll(row).toSet()

            matchResults.forEach { result ->
                val startBody = result.range.first
                if (findHead(rowIndex, startBody) && findLegs(rowIndex, startBody)) {
                    found = true
                    markSeaMonster(rowIndex, startBody)
                }
            }
        }

        return found
    }

    private fun findHead(bodyRow: Int, startBody: Int): Boolean {
        return raw[bodyRow - 1][startBody + 18] == '#'
    }

    private fun findLegs(bodyRow: Int, startBody: Int): Boolean {
        return raw[bodyRow + 1][startBody + 1] == '#'
                && raw[bodyRow + 1][startBody + 4] == '#'
                && raw[bodyRow + 1][startBody + 7] == '#'
                && raw[bodyRow + 1][startBody + 10] == '#'
                && raw[bodyRow + 1][startBody + 13] == '#'
                && raw[bodyRow + 1][startBody + 16] == '#'
    }

    private fun markSeaMonster(bodyRow: Int, startBody: Int) {
        // head
        mark[bodyRow - 1][startBody + 18] = true

        // body
        mark[bodyRow][startBody] = true
        mark[bodyRow][startBody + 5] = true
        mark[bodyRow][startBody + 6] = true
        mark[bodyRow][startBody + 11] = true
        mark[bodyRow][startBody + 12] = true
        mark[bodyRow][startBody + 17] = true
        mark[bodyRow][startBody + 18] = true
        mark[bodyRow][startBody + 19] = true

        // leg
        mark[bodyRow + 1][startBody + 1] = true
        mark[bodyRow + 1][startBody + 4] = true
        mark[bodyRow + 1][startBody + 7] = true
        mark[bodyRow + 1][startBody + 10] = true
        mark[bodyRow + 1][startBody + 13] = true
        mark[bodyRow + 1][startBody + 16] = true
    }

    private fun findSeaLevel(): Int {
        var sum = 0
        raw.forEachIndexed { rowIndex, row ->
            row.forEachIndexed { index, c ->
                if (c == '#' && !mark[rowIndex][index]) {
                    sum += 1
                }
            }
        }
        return sum
    }

    private fun rotateR() {
        val newRaw = mutableListOf<String>()
        raw.forEachIndexed { index, _ ->
            newRaw.add(raw.reversed().map { it[index] }.joinToString(""))
        }
        raw = newRaw
    }

    private fun flip() {
        raw = raw.reversed()
    }


}