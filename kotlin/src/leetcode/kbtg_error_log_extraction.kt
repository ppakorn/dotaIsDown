/*
 * Complete the 'extractErrorLogs' function below.
 *
 * The function is expected to return a 2D_STRING_ARRAY.
 * The function accepts 2D_STRING_ARRAY logs as parameter.
 */

fun extractErrorLogs(logs: Array<Array<String>>): Array<Array<String>> {
    val errorStatus = "ERROR"
    val criticalStatus = "CRITICAL"

    // Filter only ERROR and CRITICAL
    val filteredLogs = logs.filter { it[2] == errorStatus || it[2] == criticalStatus }

    // Sort by date and time and index
    // From doc of sortedBy
    // "The sort is stable. It means that equal elements preserve their order relative to each other after sorting."
    // That means if the dates and times are equal, order is preserved
    val sortedLogs = filteredLogs.sortedBy {
        val yearMonthDate = it[0].split("-").reversed().joinToString("-")
        val datetime = yearMonthDate + "T" + it[1]
        datetime
    }

    return sortedLogs.toTypedArray()
}

fun main(args: Array<String>) {
//    val logsRows = readLine()!!.trim().toInt()
//    val logsColumns = readLine()!!.trim().toInt()
//
//    val logs = Array<Array<String>>(logsRows, { Array<String>(logsColumns, { "" }) })
//
//    for (i in 0 until logsRows) {
//        logs[i] = readLine()!!.trimEnd().split(" ").toTypedArray()
//    }
//
//    val result = extractErrorLogs(logs)
//
//    println(result.map{ it.joinToString(" ") }.joinToString("\n"))

    val log1 = arrayOf("01-01-2022", "09:01", "CRITICAL", "failed")
    val log2 = arrayOf("01-01-2022", "18:00", "INFO", "failed")
    val log3 = arrayOf("01-01-2022", "09:00", "ERROR", "failed3")
    val log4 = arrayOf("01-01-2021", "18:00", "CRITICAL", "failed4")
    val result = extractErrorLogs(arrayOf(log1, log2, log3, log4))
    result.forEach {
        println(it.contentToString())
    }
}
