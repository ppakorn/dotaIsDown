package shopee

class Csv {
    fun read() {
        val csvData: String = "a,b,c\nd,e,f"
        val rows: List<Map<String, String>> = csvReader().readAllWithHeader(csvData)
        println(rows)
    }

    fun toCsv() {

    }
}