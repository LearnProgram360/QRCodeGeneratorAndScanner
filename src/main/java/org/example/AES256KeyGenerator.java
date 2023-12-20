package org.example;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class AES256KeyGenerator {
    public static void main(String[] args) {
        try {
            // Specify the algorithm for AES with 256-bit key size
            String algorithm = "AES";
            int keySize = 128; // Key size in bits

            // Generate a new AES-256 key
            SecretKey secretKey = generateAES256Key(algorithm, keySize);

            // Convert the key to a byte array (for demonstration)
            byte[] keyBytes = secretKey.getEncoded();
            System.out.println("Generated AES-256 Key (Hexadecimal): " + bytesToHex(keyBytes));
        } catch (NoSuchAlgorithmException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    public static SecretKey generateAES256Key(String algorithm, int keySize) throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator = KeyGenerator.getInstance(algorithm);
        keyGenerator.init(keySize);
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
