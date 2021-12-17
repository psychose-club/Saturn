package club.psychose.saturn.utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {
    public static void printEmptyLine () {
        System.out.println(" ");
    }

    public static void debug (String output) {
        System.out.println(getDateAndTime("CONSOLE") + " | [Saturn]: " + output);
    }

    public static String getDateAndTime (String formatMode) {
        Date date = new Date();
        DateFormat dateFormat;

        if ("CONSOLE".equals(formatMode)) {
            dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        } else {
            dateFormat = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
        }

        return dateFormat.format(date);
    }

    public static Path getOSPath (Path path) {
        return Paths.get(path.toString().trim().replace("\\", File.separator));
    }
}