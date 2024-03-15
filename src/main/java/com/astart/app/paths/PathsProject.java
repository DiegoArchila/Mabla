package com.astart.app.paths;

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
 *         &emsp; For naming the path the images path, example: <br/>
 *         <pre>public static final Path IMAGES_PATH_USERS = Paths.get(base +"//images//users");</pre>
 *         </p>
 *     </li>
 * </ul>
 */
public class PathsProject {

    private static final String base="src//main//resources//static";

    public static final Path IMAGES_PATH_USERS=Paths.get(base +"//images//users").toAbsolutePath();
    public static final Path IMAGES_PATH_PRODUCTS=Paths.get(base +"//images//products").toAbsolutePath();
}
