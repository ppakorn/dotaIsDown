package script

import java.io.File
import java.security.KeyFactory
import java.security.PrivateKey
import java.security.spec.PKCS8EncodedKeySpec
import java.util.*


fun loadPublicFromFile(publicKeyFilePath: String): PrivateKey {
    // Read the private key from the specified file
    val publicKeyPEM = File(publicKeyFilePath).readText()

    // Extract the key data between the "-----BEGIN PUBLIC KEY-----" and "-----END PUBLIC KEY-----" markers
    val privateKeyPEMData = publicKeyPEM
        .replace("-----BEGIN PUBLIC KEY-----", "")
        .replace(System.lineSeparator(), "")
        .replace("-----END PUBLIC KEY-----", "")
        .trim()

    // Decode the Base64-encoded key data
    val privateKeyBytes = Base64.getDecoder().decode(privateKeyPEMData)

    // Create a KeyFactory and generate the PrivateKey
    val keyFactory = KeyFactory.getInstance("RSA")
    val keySpec = PKCS8EncodedKeySpec(privateKeyBytes)
    return keyFactory.generatePrivate(keySpec)
}