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