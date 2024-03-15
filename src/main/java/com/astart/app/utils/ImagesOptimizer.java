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

public class ImagesOptimizer {

    private static final Integer HEIGHT =1080;
    private static final Integer WIDTH =1080;
    private static final String EXTENSION_IMAGE = ".jpg";


    /**
     * optimize the images
     * @param image
     * @param path Path where will the image is saved
     * @return Path where it is the image created
     */
    public static Path setOptimized(MultipartFile image, PathsProject path) {
        String EXTENSION_IMAGE = ".jpg";

        //Settings params of compression
        ImageWriter writerImage = ImageIO.getImageWritersByFormatName(EXTENSION_IMAGE).next();
        ImageWriteParam paramsWriteImage = new JPEGImageWriteParam(null);
        paramsWriteImage.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
        paramsWriteImage.setCompressionQuality(0.5f);

        //Create the file in filesystem, but without the data.
        Path imagePath= Path.of(path.toString() + "/" + System.currentTimeMillis() + EXTENSION_IMAGE);
        File ImageOptimized= new File(imagePath.toAbsolutePath().toString());

        /**
         * If the image exists, and its size is upper a 1MB, it's going compressed,
         * else it is saved
         */
        try {
            if (!image.isEmpty()) {
                if ((image.getSize() > 1024 * 1024)) {
                    InputStream inputStreamImageOriginal = image.getInputStream();
                    BufferedImage originalImage = ImageIO.read(inputStreamImageOriginal);

                    /* Save data image to File created, after its compressed */
                    writerImage.setOutput(ImageIO.createImageOutputStream(ImageOptimized));
                    writerImage.write(null, new IIOImage(originalImage, null, null),paramsWriteImage);

                    /* Set Objects to null for garbage collector */
                    inputStreamImageOriginal.close();
                    inputStreamImageOriginal=null;
                    originalImage=null;

                    System.out.println("Image compressed and created successfully");

                    return imagePath;
                }
            } else {
                InputStream inputStreamImageOriginal = image.getInputStream();
                BufferedImage originalImage = ImageIO.read(inputStreamImageOriginal);

                /* Save the image without compress */
                ImageIO.write(originalImage, EXTENSION_IMAGE, ImageOptimized);

                System.out.println("Image compressed and created successfully");

                return imagePath;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);

            e.printStackTrace();

            return null;
        }

    }
}
