package org.example;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class AESKeyGenerator {
    public static void main(String[] args) {
        try {
            // Specify the algorithm for AES (128, 192, or 256 bits)
            String algorithm = "AES";

            // Generate a new AES key
            SecretKey secretKey = generateAESKey(algorithm);

            // Print the generated key as a byte array (for demonstration)
            byte[] keyBytes = secretKey.getEncoded();
            System.out.println("Generated AES Key (Hexadecimal): " + bytesToHex(keyBytes));
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static SecretKey generateAESKey(String algorithm) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);

        // Set the key size (128, 192, or 256 bits)
        // For example, for AES-128, use keyGenerator.init(128);
        keyGenerator.init(128);

        // Generate and return the AES key
        return keyGenerator.generateKey();
    }

    // Helper method to convert a byte array to a hexadecimal string
    public static String bytesToHex(byte[] bytes) {
        StringBuilder hexStringBuilder = new StringBuilder();
        for (byte b : bytes) {
            hexStringBuilder.append(String.format("%02X", b));
        }
        return hexStringBuilder.toString();
    }
}
