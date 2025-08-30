package com.chat.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Logger utility for consistent timestamped logs
 */
public class LoggerUtil {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

    public static void log(String tag, String message) {
        String timestamp = sdf.format(new Date());
        System.out.println("[" + timestamp + "][" + tag + "] " + message);
    }

    public static void logInfo(String message) {
        log("INFO", message);
    }

    public static void logError(String message) {
        log("ERROR", message);
    }

    public static void logSystem(String message) {
        log("SYSTEM", message);
    }
}
