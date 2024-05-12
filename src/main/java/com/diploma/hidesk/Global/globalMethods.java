package com.diploma.hidesk.Global;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Getter
@Setter
public class globalMethods {
    public static Path currentDirectory = Paths.get(System.getProperty("user.dir"));
    public static Path rootPath = currentDirectory.resolveSibling("storage");
    public static String storagePath = rootPath.toString().replace("\\", "/");

    public static String resizeAndSaveImage(MultipartFile file, int targetWidth, int targetHeight) throws IOException {

        Path currentDirectory = Paths.get(System.getProperty("user.dir"));
        Path rootPath = currentDirectory.resolveSibling("storage/images");

        String uniqueFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        Path filePath = Paths.get(rootPath + File.separator + uniqueFileName);

        try {
            BufferedImage originalImage = ImageIO.read(file.getInputStream());
            Image resizedImage = originalImage.getScaledInstance(targetWidth, targetHeight, Image.SCALE_DEFAULT);
            BufferedImage bufferedResizedImage = new BufferedImage(targetWidth, targetHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = bufferedResizedImage.createGraphics();
            g2d.drawImage(resizedImage, 0, 0, null);
            g2d.dispose();

            // Save the resized image to file
            ImageIO.write(bufferedResizedImage, "jpg", new File(filePath.toString()));
        } catch (IOException e) {
            System.out.println(e);
        }

        return uniqueFileName;
    }
}
