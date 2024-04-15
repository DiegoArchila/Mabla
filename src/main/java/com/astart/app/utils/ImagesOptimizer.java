package com.astart.app.utils;

import com.luciad.imageio.webp.WebPWriteParam;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Path;

/**
 * Class for optimize images.
 * The System is settled for the all inputs images accept max size 5MB.
 * If needs change these value, go to file  application.properties.yml
 * <pre>spring.servlet.multipart.max-file-size=5MB</pre>
 */
public class ImagesOptimizer {

    // Constant Class
    private static final Integer STANDAR_WIDTH =1080;
    private static final String EXTENSION_IMAGE = "webp";

    public ImagesOptimizer() {
    }

    /**
     * Gets the image optimized
     *
     * @param image Image in any type image, and the output always be .webp
     * @param path  Path where will the image is saved
     * @return Path where it is the image created
     */
    public Path getOptimized(MultipartFile image, Path path) {

        //Create the file in filesystem, but without the data.
        Path imagePath = Path.of(path.toString() + "/" + System.currentTimeMillis() + "." + EXTENSION_IMAGE);
        File imageOptimized = new File(imagePath.toAbsolutePath().toString());

        //Settings params of compression
        ImageWriter writerImage = ImageIO.getImageWritersByMIMEType("image/webp").next();
        ImageWriteParam paramsWriteImage = new WebPWriteParam(writerImage.getLocale());
        paramsWriteImage.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);

        /* Adjust this parameter if you want change the quality of compression, 1.0 for best quality or 0.0 without quality */
        paramsWriteImage.setCompressionType(paramsWriteImage.getCompressionTypes()[WebPWriteParam.LOSSY_COMPRESSION]);
        paramsWriteImage.setCompressionQuality(0.2f);


        try {
            if (!image.isEmpty()) {

                InputStream inputStreamImageOriginal = image.getInputStream();
                BufferedImage originalImage = ImageIO.read(inputStreamImageOriginal);

                //If the input image is upper to STANDAR_WIDTH, this is resized, else don´t it
                if (originalImage.getWidth() > STANDAR_WIDTH){

                    //Calculate the aspect for calc the new height
                    Float aspect = ((float) originalImage.getWidth() / originalImage.getHeight());
                    Integer newHeight = (int) (STANDAR_WIDTH / aspect);


                    //Create the image with new parameters without data
                    BufferedImage newImage = new BufferedImage(STANDAR_WIDTH, newHeight, BufferedImage.TYPE_INT_RGB);

                    // Create the drawer for the new image
                    Graphics2D drawNewImage= newImage.createGraphics();
                    drawNewImage.drawImage(originalImage.getScaledInstance(
                            STANDAR_WIDTH,
                            newHeight,
                            Image.SCALE_SMOOTH
                    ),0,0,null);

                    /* Save data image to File created, after its compressed */
                    writerImage.setOutput(ImageIO.createImageOutputStream(imageOptimized));
                    writerImage.write(null, new IIOImage(newImage, null, null), paramsWriteImage);

                    drawNewImage.dispose();
                    newImage=null;

                } else {
                    /* Save data image to File created, after its compressed */
                    writerImage.setOutput(ImageIO.createImageOutputStream(imageOptimized));
                    writerImage.write(null, new IIOImage(originalImage, null, null), paramsWriteImage);
                }

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
