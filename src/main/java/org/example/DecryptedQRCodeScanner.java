package org.example;

import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class DecryptedQRCodeScanner {
    public static void main(String[] args) {
        try {
            // Specify the file path of the QR code image to scan
            String filePath = "D:\\IdeaProjects\\qrcode.png";

            // Read the QR code image
            File qrCodeFile = new File(filePath);
            BufferedImage bufferedImage = ImageIO.read(qrCodeFile);

            if (bufferedImage != null) {
                LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                Reader reader = new MultiFormatReader();
                try {
                    Result result = reader.decode(bitmap);

                    // Get the encrypted text from the QR code
                    String encryptedText = result.getText();

                    // Decrypt the encrypted text
                    String decryptionKey = "6DC39B405F5A36743E8F6506D366D9D8"; // Replace with your encryption key
                    String decryptedText = decrypt(encryptedText, decryptionKey);

                    System.out.println("Decrypted QR Code Text: " + decryptedText);
                } catch (ChecksumException e) {
                    System.err.println("ChecksumException: " + e.getMessage());
                } catch (FormatException e) {
                    System.err.println("FormatException: " + e.getMessage());
                }
            } else {
                System.err.println("Failed to read the QR code image.");
            }
        } catch (IOException | NotFoundException | DecryptionException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static String decrypt(String encryptedText, String key) throws DecryptionException {
        try {
            // Create a SecretKeySpec object from the encryption key
            SecretKeySpec secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            // Initialize the Cipher for decryption
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            // Decode the Base64-encoded encrypted text
            byte[] encryptedBytes = Base64.getDecoder().decode(encryptedText);

            // Decrypt the encrypted bytes
            byte[] decryptedBytes = cipher.doFinal(encryptedBytes);

            // Convert the decrypted bytes back to a string
            return new String(decryptedBytes, "UTF-8");
        } catch (Exception e) {
            throw new DecryptionException("Decryption failed: " + e.getMessage());
        }
    }

    // Custom exception for decryption errors
    static class DecryptionException extends Exception {
        public DecryptionException(String message) {
            super(message);
        }
    }
}
