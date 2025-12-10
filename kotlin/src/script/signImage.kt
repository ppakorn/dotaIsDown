package script

import java.io.File
import java.net.URI
import java.net.http.HttpClient
import java.net.http.HttpRequest
import java.net.http.HttpResponse
import java.nio.charset.StandardCharsets
import java.nio.file.Files
import java.nio.file.Paths
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.PublicKey
import java.security.Signature
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*

// openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in pkcs1.pem -out pkcs8.pem

fun main() {
    // image
    val imageFilePath = "src/script/iu.jpeg"
    val imageBytes = imageToByteArray(imageFilePath)
//    println(byteArrayToBase64(imageBytes))
//    val imageBytes = "content2".toByteArray()

    // metadata
    val metadata = "{\"timestamp\": \"12345678\", \"setNumOfAction\": 5, \"actualNumOfAction\": 2}"
    val metadataBytes = metadata.toByteArray(StandardCharsets.UTF_8)

    // data = image + timestamp
    val data = imageBytes.plus(metadataBytes)

    // private key
    val privateKey = loadPrivateKeyFromFile("/Users/pakorn.t/dotaIsDown/kotlin/src/script/private-uat-661f542d986e2365cc000dfa-8.pem")
//    val privateKey = loadPrivateKeyFromString(privateKey)

    // Sign the data
    val signatureInstance = Signature.getInstance("SHA512withRSA")
    signatureInstance.initSign(privateKey)
    signatureInstance.update(data)
    val signature = signatureInstance.sign()


    println(Base64.getEncoder().encodeToString(signature))

//    val result = callAPI("https://uat.nonprod-api.ainu.tech/v1/face/quality", imageBytes)
//    println(result)
}

fun imageToByteArray(imageFilePath: String): ByteArray {
    val imageFile = File(imageFilePath)
    if (!imageFile.exists()) {
        throw Exception("Image file does not exist.")
    }
    return Files.readAllBytes(Paths.get(imageFilePath))
}

fun byteArrayToBase64(bytes: ByteArray): String {
    return Base64.getEncoder().encodeToString(bytes)
}

fun loadPrivateKeyFromFile(privateKeyFilePath: String): PrivateKey {
    // Read the private key from the specified file
    val privateKeyPEM = File(privateKeyFilePath).readText()
    return processPrivateKey(privateKeyPEM)
}

fun loadPrivateKeyFromString(s: String): PrivateKey {
    return processPrivateKey(s)
}

fun processPrivateKey(before: String): PrivateKey {
    // Extract the key data between the "-----BEGIN PRIVATE KEY-----" and "-----END PRIVATE KEY-----" markers
    val privateKeyPEMData = before
        .replace("-----BEGIN RSA PRIVATE KEY-----", "")
        .replace(System.lineSeparator(), "")
        .replace("-----END RSA PRIVATE KEY-----", "")
        .replace("-----BEGIN PRIVATE KEY-----", "")
        .replace("-----END PRIVATE KEY-----", "")
        .trim()

    // Decode the Base64-encoded key data
    val privateKeyBytes = Base64.getDecoder().decode(privateKeyPEMData)

    // Create a KeyFactory and generate the PrivateKey
    val keyFactory = KeyFactory.getInstance("RSA")
    val keySpec = PKCS8EncodedKeySpec(privateKeyBytes)
    return keyFactory.generatePrivate(keySpec)
}

fun loadPublicKeyFromFile(publicKeyFilePath: String): PublicKey {
    // Read the private key from the specified file
    val privateKeyPEM = File(publicKeyFilePath).readText()
    return processPublicKey(privateKeyPEM)
}

fun processPublicKey(before: String): PublicKey {
    val publicKeyPEMData = before
        .replace("-----BEGIN RSA PUBLIC KEY-----", "")
        .replace(System.lineSeparator(), "")
        .replace("-----END RSA PUBLIC KEY-----", "")
        .replace("-----BEGIN PUBLIC KEY-----", "")
        .replace("-----END PUBLIC KEY-----", "")
        .trim()

    // Decode the Base64-encoded key data
    val publicKeyBytes = Base64.getDecoder().decode(publicKeyPEMData)

    // Create a KeyFactory and generate the PrivateKey
    val keyFactory = KeyFactory.getInstance("RSA")
    val keySpec = PKCS8EncodedKeySpec(publicKeyBytes)
    return keyFactory.generatePublic(keySpec)
}

fun callAPI(url: String, imageBytes: ByteArray): HttpResponse<String> {
    val boundary = UUID.randomUUID().toString()
    val client = HttpClient.newBuilder().build()

    val bodyBuilder = StringBuilder()
    bodyBuilder.append("--$boundary\r\n")
    bodyBuilder.append("Content-Disposition: form-data; name=\"image\"; filename=\"image\"\r\n")
    bodyBuilder.append("Content-Type: image/*\r\n\r\n")

    val requestBody = HttpRequest.BodyPublishers.ofByteArrays(listOf(
        bodyBuilder.toString().toByteArray(),
        imageBytes,
        "\r\n--$boundary--\r\n".toByteArray()
    ))

    val request = HttpRequest.newBuilder()
        .uri(URI.create(url))
        .header("Content-Type", "multipart/form-data; boundary=$boundary")
        .POST(requestBody)
        .build()

    return client.send(request, HttpResponse.BodyHandlers.ofString())
}
