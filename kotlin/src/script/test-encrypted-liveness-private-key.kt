import script.loadPrivateKeyFromFile
import java.nio.charset.StandardCharsets
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.spec.MGF1ParameterSpec
import java.security.spec.PKCS8EncodedKeySpec
import javax.crypto.Cipher
import java.util.Base64
import javax.crypto.spec.*

fun main() {
    val b64DEK = "zGkl9BKR0kK9dlrbyPmn28UcpDPzF9id40e+zynTbTQelCoDMMt2JDEKsE2B8cPRfYASJXam7+1Lk6MnkL24zPnswUtkdXYS/uxoGRcUfmwzGhE82qQt4IX6KARrQJMF4T0Z2bYqTzH0cx2hnLlL/CPtDXNlaxdAUuUDAx7BrJ34tCmudrrCi+ZANRahZyA0W49qXIoJkRL6L7fBLVT7HCrvxXgY2yL8ZVUQLSbjYPvhR/M4/bDMkgfd8iWiiM1lqyb63iRakKj/xJPEuRkcvpjKeSiL8CoJlwpdZJWKB6ate5RMkQJQtkzuyRBU5SLUQM2op34g7/AaV4/e3LNF4f8thZB/wii/QKAYWlvBivB6lKVPVeLdqD7nndPK9x9k6t12CWKixNreeCHrcTWRJ+jJBaF/YbarTunZfeQdsDH0qHmj+A4SPUiujI3yLFVdRYhUJjUImi79n7UcRazRO0MpRq2Wj0V0+KYXjlHrwVoWUTp07ba54xzW4Ck3QKTPJDBBKUjFQbdDINsxZlTSt1slGoktvQ4bhMOMwd7VOkTClhkiK/fEGkN7LYw9q1Vy4++uzXYsHycetldTPM8FJEJytlKxkstR+yTJmF/0iPnB9cfXb0T0loR9fCb/1Dh78Fn9ExQpSU3OmPCfLNZNrR4naHKYISuzfk6WScCaGgQ="
    val b64Data = "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"
    val masterPrivateKeyPEM = loadPrivateKeyFromFile("src/script/private-dev-master3.0-8.pem")

    try {
        // Step 1: Decrypt DEK using the master private key
        val dek = decryptDEK(b64DEK, masterPrivateKeyPEM)

        // Step 2: Decrypt the data using DEK
        val decryptedData = decryptData(b64Data, dek)
        println("Decrypted Data: $decryptedData")
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

/**
 * Decrypts the base64-encoded DEK using the provided PEM-formatted RSA private key.
 *
 * @param b64DEK Base64-encoded encrypted DEK.
 * @param pemPrivateKey PEM-formatted RSA private key (PKCS#8).
 * @return Decrypted DEK as a byte array.
 */
fun decryptDEK(b64DEK: String, privateKey: PrivateKey): ByteArray {
    // Initialize Cipher for RSA decryption with OAEP padding using SHA-256
    val oaepParams = OAEPParameterSpec(
        "SHA-256",
        "MGF1",
        MGF1ParameterSpec.SHA256,
        PSource.PSpecified.DEFAULT
    )
    val cipher = Cipher.getInstance("RSA/ECB/OAEPPadding")
    cipher.init(Cipher.DECRYPT_MODE, privateKey, oaepParams)

    // Decode the Base64-encoded DEK
    val encryptedDEK = Base64.getDecoder().decode(b64DEK)

    // Decrypt the DEK
    return cipher.doFinal(encryptedDEK)
}

/**
 * Decrypts the base64-encoded data using the provided DEK.
 *
 * @param b64Data Base64-encoded encrypted data.
 * @param dek Decrypted DEK as a byte array.
 * @return Decrypted data as a string.
 */
fun decryptData(b64Data: String, dek: ByteArray): String {
    // Decode the Base64-encoded data
    val encryptedData = Base64.getDecoder().decode(b64Data)
    println("Encrypted Data Length: ${encryptedData.size} bytes")

    // Define AES-GCM parameters
    val nonceSize = 12 // 12 bytes is standard for GCM
    val tagSize = 16 // 16 bytes (128 bits) authentication tag

    // Ensure the encrypted data is at least nonceSize + tagSize bytes
    if (encryptedData.size < nonceSize + tagSize) {
        throw IllegalArgumentException("Encrypted data is too short to contain nonce and authentication tag.")
    }

    // Extract the nonce and ciphertext + tag
    val nonce = encryptedData.copyOfRange(0, nonceSize)
    val ciphertextAndTag = encryptedData.copyOfRange(nonceSize, encryptedData.size)

    println("Nonce (Hex): ${nonce.joinToString("") { "%02x".format(it) }}")
    println("Ciphertext + Tag Length: ${ciphertextAndTag.size} bytes")

    // Initialize Cipher for AES-GCM decryption
    val cipher = Cipher.getInstance("AES/GCM/NoPadding")
    val keySpec = SecretKeySpec(dek, "AES")
    val gcmSpec = GCMParameterSpec(tagSize * 8, nonce) // Tag size in bits
    cipher.init(Cipher.DECRYPT_MODE, keySpec, gcmSpec)

    // Decrypt the ciphertext
    val decryptedBytes = cipher.doFinal(ciphertextAndTag)

    // Convert decrypted bytes to string (assuming UTF-8 encoding)
    return String(decryptedBytes, StandardCharsets.UTF_8)
}
