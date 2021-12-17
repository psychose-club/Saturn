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

        StringUtils.printEmptyLine();

        // Running scanner.
        new LogScanner().scanMinecraftLogs();
    }
}