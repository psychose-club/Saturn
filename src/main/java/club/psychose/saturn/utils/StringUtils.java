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