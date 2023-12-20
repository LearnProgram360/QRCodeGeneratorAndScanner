package org.example;

import java.awt.image.BufferedImage;
import java.io.File;
//import java.io.IOException;
import javax.imageio.ImageIO;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.common.BitMatrix;

public class QRCodeGenerator {
    public static void main(String[] args) {
        String qrCodeText = "giritharan.t@paytm"; // Text you want to encode in the QR code
        int width = 300; // Width of the QR code
        int height = 300; // Height of the QR code
        String filePath = "D:\\IdeaProjects\\qrcode.png"; // File path where the QR code image will be saved

        try {
            // Create a BitMatrix object that represents the QR code
            BitMatrix bitMatrix = new MultiFormatWriter().encode(qrCodeText, BarcodeFormat.QR_CODE, width, height);

            // Convert the BitMatrix to a BufferedImage
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
                }
            }

            // Save the BufferedImage as a PNG image
            File qrCodeFile = new File(filePath);
            ImageIO.write(image, "png", qrCodeFile);

            System.out.println("QR code saved to: " + filePath);
        } catch (Exception e) {
            System.err.println("Error generating QR code: " + e.getMessage());
        }
    }
}
