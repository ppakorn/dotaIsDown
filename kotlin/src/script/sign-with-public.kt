package script

import java.security.KeyFactory
import java.security.spec.MGF1ParameterSpec
import java.security.spec.X509EncodedKeySpec
import java.util.*
import javax.crypto.Cipher
import javax.crypto.spec.OAEPParameterSpec
import javax.crypto.spec.PSource

fun encryptDataWithRSAPublicKey(data: ByteArray, publicKeyBytes: ByteArray): ByteArray? {
    try {
        // Create public key from bytes
        val keySpec = X509EncodedKeySpec(publicKeyBytes)
        val keyFactory = KeyFactory.getInstance("RSA")
        val publicKey = keyFactory.generatePublic(keySpec)

        // Initialize cipher for encryption
        val cipher = Cipher.getInstance("RSA/ECB/OAEPPadding")
        val oaepParams = OAEPParameterSpec("SHA-256", "MGF1", MGF1ParameterSpec("SHA-256"), PSource.PSpecified.DEFAULT)
        cipher.init(Cipher.ENCRYPT_MODE, publicKey, oaepParams)

        // Encrypt the data
        return cipher.doFinal(data)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return null
}

fun main() {
    // Sample data to be encrypted
    val originalData = "Hello, Bob!".toByteArray()

    // Bob's public key bytes (replace with actual public key bytes)
    val publicKey = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBITANBgkqhkiG9w0BAQEFAAOCAQ4AMIIBCQKCAQBspvHIubW89F6Zoxp2OUAV\n" +
            "UHESzhNZzeLJussibSZ868ZTvkD5aVsd61gnIUJ5ntbBgQrZ+RMAnfzOoOJy0BdT\n" +
            "M/8GSjnJuf/4tpq9q8n68zB0qz4IIr8BMN3LEx+11SUY6s6mD/GROS/fkFAv43ay\n" +
            "/cwr8dX1QNqigUNZaG2f5HIb0ubjSNpgaQBvEYgnQyXPNrCgofChCSmrqN9tRM20\n" +
            "+JxvL+AOLDznIo4SlQVDCljnJHmvaibXG321FL1Kqs6ZsAY2UMd008EgyF3A+M/t\n" +
            "jZ9dZR/fStWbydErxekeZorTKZdYoIKQjX9JnuHPAkIKvbTUpKJNooeFC3FDNhqX\n" +
            "AgMBAAE=\n" +
            "-----END PUBLIC KEY-----"
    val publicKeyPEMData = publicKey
        .replace("-----BEGIN RSA PUBLIC KEY-----", "")
        .replace(System.lineSeparator(), "")
        .replace("-----END RSA PUBLIC KEY-----", "")
        .replace("-----BEGIN PUBLIC KEY-----", "")
        .replace("-----END PUBLIC KEY-----", "")
        .trim()

    // Decode the Base64-encoded key data
    val publicKeyBytes = Base64.getDecoder().decode(publicKeyPEMData)

    // Encrypt the data using Bob's public key
    val encryptedData = encryptDataWithRSAPublicKey(originalData, publicKeyBytes)
    val encryptedDataBase64 = Base64.getEncoder().encode(encryptedData)
    if (encryptedData != null) {
        println("Encrypted data: ${String(encryptedDataBase64)}")
    } else {
        println("Encryption failed")
    }
}
