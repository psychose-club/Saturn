package club.psychose.saturn.core.system.managers;

import club.psychose.saturn.utils.Constants;
import club.psychose.saturn.utils.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class FileManager {
    public FileManager () {
        try {
            if (!(Files.exists(Constants.getSaturnFolderPath(null))))
                Files.createDirectories(Constants.getSaturnFolderPath(null));

            if (!(Files.exists(Constants.getSaturnFolderPath("\\logs\\"))))
                Files.createDirectories(Constants.getSaturnFolderPath("\\logs\\"));
        } catch (IOException ioException) {
            StringUtils.debug("IOException while creating the directories!");
            ioException.printStackTrace();
        }
    }

    public void saveArrayListToAFile (Path outputPath, ArrayList<String> arrayListToSave) {
        StringBuilder stringBuilder = new StringBuilder();

        for (String line : arrayListToSave) {
            stringBuilder.append(line).append("\n");
        }

        try {
            Files.write(outputPath, stringBuilder.toString().getBytes());
        } catch (IOException ioException) {
            StringUtils.debug("IOException while saving an ArrayList to a file!");
            ioException.printStackTrace();
        }
    }
}