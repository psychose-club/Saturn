/*
 * Copyright © 2021 psychose.club
 * Contact: psychose.club@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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