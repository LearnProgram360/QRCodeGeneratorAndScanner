package org.example;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.security.Key;
import java.util.Base64;

public class EncryptedQRCodeGenerator {
    public static void main(String[] args) {
        try {
            // Your sensitive data
            String qrCodeText = "Welcome!!!!";

            // Encryption key (16 bytes for AES-128)
            String encryptionKey = "6DC39B405F5A36743E8F6506D366D9D8";

            // Encrypt the QR code text
            String encryptedText = encrypt(qrCodeText, encryptionKey);

            int width = 300; // Width of the QR code
            int height = 300; // Height of the QR code

            BitMatrix bitMatrix = new MultiFormatWriter().encode(encryptedText, BarcodeFormat.QR_CODE, width, height);

            // Convert the BitMatrix to a BufferedImage
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }

            // Specify the file path where you want to save the QR code image
            String filePath = "D:\\IdeaProjects\\qrcode.png";

            // Save the QR code image to the specified file path
            File qrCodeFile = new File(filePath);
            ImageIO.write(image, "png", qrCodeFile);

            System.out.println("QR code generated with encrypted text and saved to: " + filePath);
            System.out.println("Encrypted Data: " +encryptedText);
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static String encrypt(String text, String key) throws Exception {
        Key secretKey = new SecretKeySpec(key.getBytes("UTF-8"), "AES");
        Cipher cipher = Cipher.getInstance("AES");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] encryptedBytes = cipher.doFinal(text.getBytes("UTF-8"));
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }
}
