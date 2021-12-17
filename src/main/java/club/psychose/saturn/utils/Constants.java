package club.psychose.saturn.utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Locale;

public class Constants {
    public static final String VERSION = "1.0.0";
    public static final String BUILD = "1";

    public static Path getSaturnFolderPath (String additionalPath) {
        return additionalPath != null ? StringUtils.getOSPath(Paths.get(System.getProperty("user.home") + "\\psychose.club\\Saturn\\" + additionalPath)) : StringUtils.getOSPath(Paths.get(System.getProperty("user.home") + "\\psychose.club\\Saturn\\"));
    }

    public static Path getSystemMinecraftFolder () {
        String runningOS = System.getProperty("os.name").toUpperCase(Locale.ROOT);

        if (runningOS.contains("WINDOWS")) {
            return StringUtils.getOSPath(Paths.get(System.getenv("APPDATA") + "\\.minecraft\\"));
        } else if (runningOS.contains("MAC")) {
            return StringUtils.getOSPath(Paths.get(System.getProperty("user.home") + "\\Library\\Application Support\\minecraft"));
        } else {
            return StringUtils.getOSPath(Paths.get("~/.minecraft"));
        }
    }
}