package utils;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathHolder {
    private static final String RES_FOLDER = "src/main/resources";
    private static final String USER_FOLDER = System.getProperty("user.dir");
    public static final Path APP_HOME_PATH = Paths.get(USER_FOLDER);


    public static Path getPathToFile(String path)
    {
        return APP_HOME_PATH.resolve(Paths.get(path));
    }

    public static Path getPathToResourceFile(String path)
    {
        return APP_HOME_PATH.resolve(Paths.get(RES_FOLDER, path));
    }
}
