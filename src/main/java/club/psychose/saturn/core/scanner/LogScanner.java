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

package club.psychose.saturn.core.scanner;

import club.psychose.saturn.Saturn;
import club.psychose.saturn.utils.Constants;
import club.psychose.saturn.utils.StringUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.zip.GZIPInputStream;

public class LogScanner {
    /**
     * This method scans the minecraft logs for potential exploit codes.
     * It'll scan also for known bypasses!
     * Feel free to fork and create a pull request if a new bypass is not added here!
     */

    public void scanMinecraftLogs () {
        // Initialize ArrayLists.
        ArrayList<File> exploitedFilesArrayList = new ArrayList<>();
        ArrayList<String> exploitInfosArrayList = new ArrayList<>();
        ArrayList<File> validFilesArrayList;

        StringUtils.debug("Looking up for minecraft folder...");

        // Try to use the default minecraft folder path.
        Path minecraftFolderPath = Constants.getSystemMinecraftFolder();

        // Checks if the folder it's valid and ask the user to enter the folder if it's not valid!
        while (true) {
            Scanner scanner = new Scanner(System.in);

            if (!(Files.exists(minecraftFolderPath))) {
                StringUtils.debug("Sorry! We can't find your minecraft folder!");
                StringUtils.debug("Please enter the path: ");

                String path = scanner.nextLine();

                if (path != null) {
                    if (Files.exists(Paths.get(path))) {
                        minecraftFolderPath = Paths.get(path);
                        StringUtils.debug("Thank you! We found the path!");
                        break;
                    }
                }
            } else {
                StringUtils.debug("Minecraft folder automatically found!");
                break;
            }
        }

        StringUtils.printEmptyLine();
        StringUtils.debug("Collecting logs...");

        File directoryFile = StringUtils.getOSPath(Paths.get(minecraftFolderPath + "\\logs\\")).toFile();

        // It'll check if the file is a directory and collecting all ".gz" and ".log" files!
        if (directoryFile.isDirectory()) {
            if (directoryFile.listFiles() != null) {
                validFilesArrayList = Arrays.stream(directoryFile.listFiles()).filter(File::isFile).filter(file -> (file.getName().endsWith(".gz")) || (file.getName().endsWith(".log"))).collect(Collectors.toCollection(ArrayList::new));

                StringUtils.debug("(" + validFilesArrayList.size() + ") logs collected!");
                StringUtils.debug("Scanning logs for CVE-2021-44228...");

                // It'll scan the collected files and uses a specific method to scan the files.
                for (File file : validFilesArrayList) {
                    List<String> logLinesArrayList = null;

                    if (file.getName().endsWith(".gz")) {
                        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new GZIPInputStream(new FileInputStream(file))))) {
                            logLinesArrayList = new ArrayList<>();

                            String line;
                            while ((line = bufferedReader.readLine()) != null)
                                logLinesArrayList.add(line);
                        }
                        catch (FileNotFoundException fileNotFoundException) {
                            StringUtils.debug("FileNotFoundException while checking the file " + file.toPath() + "!");
                            fileNotFoundException.printStackTrace();
                        }
                        catch (IOException ioException) {
                            StringUtils.debug("IOException while checking the file " + file.toPath() + "!");
                            ioException.printStackTrace();
                        }
                    } else if (file.getName().endsWith(".log")) {
                        try {
                            logLinesArrayList = Files.readAllLines(file.toPath(), StandardCharsets.ISO_8859_1);
                        } catch (IOException ioException) {
                            StringUtils.debug("IOException while checking the file " + file.toPath() + "!");
                            ioException.printStackTrace();
                        }
                    }

                    // It'll check if the log is not empty and scan the file for potential exploits.
                    if (logLinesArrayList != null) {
                        StringUtils.debug("Checking the file " + file.toPath() + "...");

                        boolean foundExploit = false;
                        boolean foundPotentialExploit = false;
                        for (String logLine : logLinesArrayList) {
                            if (logLine.contains("${")) {
                                foundPotentialExploit = true;

                                if (logLine.contains("jndi")) {
                                    foundExploit = true;
                                } else if (logLine.contains("ldap")) {
                                    foundExploit = true;
                                }

                                if (!(exploitedFilesArrayList.contains(file)))
                                    exploitedFilesArrayList.add(file);

                                String exploitInfoString = foundExploit ? "EXPLOIT FOUND IN " + file.getName() + "! | Detected Line: " + logLine : "POTENTIAL EXPLOIT FOUND IN " + file.getName() + "! | Detected Line: " + logLine;

                                if (!(exploitInfosArrayList.contains(exploitInfoString)))
                                    exploitInfosArrayList.add(exploitInfoString);
                            }
                        }

                        StringUtils.debug(foundPotentialExploit ? foundExploit ? "EXPLOIT FOUND IN " + file.toPath() + "!" : "POTENTIAL EXPLOIT FOUND IN " + file.toPath() + "!" : "CLEAN! " + file.toPath());
                    } else {
                        StringUtils.debug("WARNING! The file " + file.toPath() + " cannot read!");
                    }
                }

                StringUtils.printEmptyLine();

                if (exploitedFilesArrayList.size() != 0) {
                    StringUtils.debug("We found some potential exploit code in your logs!");
                    StringUtils.debug("What you can do now?");
                    StringUtils.debug("View the log files and check if not a false positive happened!");
                    StringUtils.debug("If you got infected please turn off your internet, backup your data and then format your drive, you can probably nothing do if the virus is good hidden in your files!");

                    Path logFolderPath = Constants.getSaturnFolderPath("\\logs\\" + StringUtils.getDateAndTime("FOLDER") + "\\");

                    try {
                        Files.createDirectories(StringUtils.getOSPath(Paths.get(logFolderPath + "\\files\\")));

                        Saturn.FILE_MANAGER.saveArrayListToAFile(StringUtils.getOSPath(Paths.get(logFolderPath + "\\log.txt")), exploitInfosArrayList);

                        for (File file : exploitedFilesArrayList) {
                            try {
                                Files.copy(file.toPath(), StringUtils.getOSPath(Paths.get(logFolderPath + "\\files\\" + file.getName())));
                            } catch (IOException ioException) {
                                StringUtils.debug("IOException while copying original log file!");
                                ioException.printStackTrace();
                            }
                        }
                    } catch (IOException ioException) {
                        StringUtils.debug("IOException while creating the directories!");
                        ioException.printStackTrace();
                    }

                    StringUtils.debug("You can view the log files under \"" + logFolderPath + "\"!");
                } else {
                    StringUtils.debug("Great! You are probably not infected by any Remote Code execution that happened over minecraft!");
                    StringUtils.debug("Stay safe and thank you for using this tool <3");
                }

                return;
            }
        }

        StringUtils.debug("Collecting logs failed!");
        StringUtils.debug("Maybe the directory is empty or no log files found!");
    }
}