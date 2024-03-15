package com.astart.app.utils;

import com.astart.app.paths.PathsProject;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.plugins.jpeg.JPEGImageWriteParam;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * Class for optimize images.
 * The System is settled for the all inputs images accept max size 2MB.
 * If needs change these value, go to file  application.properties.yml
 * <pre>spring.servlet.multipart.max-file-size=2MB</pre>
 */
public class ImagesOptimizer {

    // Constant Class
    private static final Integer HEIGHT =1080;
    private static final Integer WIDTH =1080;
    private static final String EXTENSION_IMAGE = "webp";


    private static Path imagePath;
    private static File ImageOptimized;


    /**
     * optimize the images
     * @param image Image in any type image, and the output always be .webp, or change the constant
     * @param path Path where will the image is saved
     * @return Path where it is the image created
     */
    public static Path getOptimized(MultipartFile image, PathsProject path) {

        //Create the file in filesystem, but without the data.
        imagePath= Path.of(path.toString() + "/" + System.currentTimeMillis() + "." +EXTENSION_IMAGE);
        ImageOptimized = new File(imagePath.toAbsolutePath().toString());

        //Settings params of compression
        ImageWriter writerImage = ImageIO.getImageWritersByFormatName(EXTENSION_IMAGE).next();
        ImageWriteParam paramsWriteImage = new JPEGImageWriteParam(null);
        paramsWriteImage.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        paramsWriteImage.setCompressionQuality(0.8f);

        try {
            if (!image.isEmpty()) {

                InputStream inputStreamImageOriginal = image.getInputStream();
                BufferedImage originalImage = ImageIO.read(inputStreamImageOriginal);

                /* Save data image to File created, after its compressed */
                writerImage.setOutput(ImageIO.createImageOutputStream(ImageOptimized));
                writerImage.write(null, new IIOImage(originalImage, null, null), paramsWriteImage);

                /* Set Objects to null for garbage collector */
                inputStreamImageOriginal.close();
                inputStreamImageOriginal = null;
                writerImage.dispose();
                originalImage = null;

                System.out.println("Image compressed and created successfully");

                return imagePath;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }
}
