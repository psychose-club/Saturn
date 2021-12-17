package club.psychose.saturn;

import club.psychose.saturn.core.scanner.LogScanner;
import club.psychose.saturn.core.system.managers.FileManager;
import club.psychose.saturn.utils.Constants;
import club.psychose.saturn.utils.StringUtils;

public class Saturn {
    // Initialize the file manager.
    public static final FileManager FILE_MANAGER = new FileManager();

    // Running main method.
    public static void main (String[] arguments) {
        new Saturn().initializeSaturn();
    }

    // Initialize Saturn.
    private void initializeSaturn () {
        System.out.println("  _________       __                       \n" +
                " /   _____/____ _/  |_ __ _________  ____  \n" +
                " \\_____  \\\\__  \\\\   __\\  |  \\_  __ \\/    \\ \n" +
                " /        \\/ __ \\|  | |  |  /|  | \\/   |  \\\n" +
                "/_______  (____  /__| |____/ |__|  |___|  /\n" +
                "        \\/     \\/                       \\/ \n" +
                "\n");
        StringUtils.debug("Copyright © 2021 psychose.club");
        StringUtils.debug("Version: " + Constants.VERSION);
        StringUtils.debug("Build Version: " + Constants.BUILD);

        // Running scanner.
        new LogScanner().scanMinecraftLogs();
    }
}