package org.example;

import com.google.zxing.*;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class QRCodeScanner {
    public static void main(String[] args) {
        String filePath = "D:\\IdeaProjects\\qrcode.png"; // Path to the QR code image file

        try {
            File qrCodeFile = new File(filePath);
            BufferedImage bufferedImage = ImageIO.read(qrCodeFile);

            if (bufferedImage != null) {
                LuminanceSource source = new BufferedImageLuminanceSource(bufferedImage);
                BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));

                Reader reader = new MultiFormatReader();
                Result result = reader.decode(bitmap);

                System.out.println("QR Code Text: " + result.getText());
            } else {
                System.err.println("Failed to read the QR code image.");
            }
        } catch (IOException | NotFoundException e) {
            System.err.println("Error reading or decoding the QR code: " + e.getMessage());
        } catch (ChecksumException e) {
            throw new RuntimeException(e);
        } catch (FormatException e) {
            throw new RuntimeException(e);
        }
    }
}

