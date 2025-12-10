//
//import com.google.gson.FieldNamingPolicy
//import com.google.gson.GsonBuilder
//import java.io.BufferedReader
//import java.io.InputStreamReader
//import java.net.URL
//import javax.net.ssl.HttpsURLConnection
//
//
///*
// * Complete the 'getRelevantFoodOutlets' function below.
// *
// * The function is expected to return a STRING_ARRAY.
// * The function accepts following parameters:
// *  1. STRING city
// *  2. INTEGER maxCost
// * URL for cut and paste
// * https://jsonmock.hackerrank.com/api/food_outlets?city=<city>&page=<pageNumber>
// */
//
//data class FoodOutlet(
//    var name: String? = null,
//    var estimatedCost: Int? = null
//)
//
//data class FoodOutletsResponse(
//    var page: Int? = null,
//    var perPage: Int? = null,
//    var total: Int? = null,
//    var totalPages: Int? = null,
//    var data: List<FoodOutlet>? = null
//)
//
//fun getRelevantFoodOutlets(city: String, maxCost: Int): Array<String> {
//    // The test cases expect page to start with 1 (should be 0)
//    // Order of response matters
//    var page = 1
//    val result = mutableListOf<String>()
//    var totalPages = 1
//
//    // Call page 0 and check totalPages from the response of page 0
//    while (page <= totalPages) {
//        val response = getFoodOutlets(city, page)
//
//        // Filter outlets that estimatedCost <= maxCost
//        response.data?.let { outlets ->
//            result.addAll(
//                outlets.filter {
//                    haveNameAndCost(it) &&
//                    it.estimatedCost!! <= maxCost
//                }.map { it.name!! }
//            )
//        }
//
//        // Continue calling next page
//        totalPages = response.totalPages ?: 1
//        page += 1
//    }
//
//    return result.toTypedArray()
//}
//
//fun makeUrl(city: String, page: Int): URL {
//    return URL("https://jsonmock.hackerrank.com/api/food_outlets?city=$city&page=$page")
//}
//
//fun getFoodOutlets(city: String, page: Int): FoodOutletsResponse {
//    // Set up connection
//    val url = makeUrl(city, page)
//    val connection = url.openConnection() as HttpsURLConnection
//    connection.requestMethod = "GET"
//    connection.doOutput = true
//
//    // If response code is successful, read from inputStream
//    // If not, read from errorStream
//    var error = false
//    val br: BufferedReader = if (connection.responseCode in 100..399) {
//        BufferedReader(InputStreamReader(connection.inputStream))
//    } else {
//        error = true
//        BufferedReader(InputStreamReader(connection.errorStream))
//    }
//
//    // Read from InputStreamReader to string
//    var responseString = ""
//    br.use {
//        var line: String?
//        while (br.readLine().also { line = it } != null) {
//            responseString += line
//            responseString += "\n"
//        }
//    }
//
//    // If error, throw exception with information
//    if (error) {
//        throw Exception("Failed to call $url. $responseString")
//    }
//
//    // If success, map to object and return
//    val gson = GsonBuilder()
//        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//        .create()
//    return gson.fromJson(responseString, FoodOutletsResponse::class.java)
//}
//
//fun haveNameAndCost(outlet: FoodOutlet): Boolean {
//    return outlet.name != null && outlet.estimatedCost != null
//}
//
//fun script.main(args: Array<String>) {
//    val city = readLine()!!
//
//    val maxCost = readLine()!!.trim().toInt()
//
//    val result = getRelevantFoodOutlets(city, maxCost)
//
//    println(result.joinToString("\n"))
//
////    println(getRelevantFoodOutlets("Houston", 30).contentToString())
//}
