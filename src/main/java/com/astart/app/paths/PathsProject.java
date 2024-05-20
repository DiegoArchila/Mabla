package com.astart.app.paths;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;

/**
 * <h3>Paths Projects Class</h3>
 * <p>
 *  This Class storage all paths of elements on this project.
 *  Use do it, in case of a path not found here, please add it.
 * </p>
 * <ul> <strong>Making Names Paths:</strong>
 *     &emsp; <li>
 *         DIRECTORY-NAME_+PATH+_RESOURCE <br/>
 *         <p>
 *         &emsp; For naming the path the users path, example: <br/>
 *         <pre>public static final Path IMAGES_PATH_USERS = Paths.get(base +"//images//users");</pre>
 *         </p>
 *     </li>
 * </ul>
 */
public class PathsProject {

    private static final String base="src//main//resources//static//";

    private static final String IMAGES_BASE_PATH = "//images//";

    public static final Path IMAGES_PATH_USERS;

    /**
     * Validate if the path exists, else it's created
     */
    static {
        try {
            IMAGES_PATH_USERS = createDirectory(base + IMAGES_BASE_PATH + "users");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static final Path IMAGES_PATH_PRODUCTS;

    static {
        try {
            IMAGES_PATH_PRODUCTS = createDirectory(base + IMAGES_BASE_PATH + "products");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Path createDirectory(String directoryPath) throws IOException {
        Path path = Paths.get(directoryPath);
        if (!Files.exists(path)) {
            Files.createDirectories(path);
            System.out.println("Directory created: " + path);
        }
        return path;
    }
}
