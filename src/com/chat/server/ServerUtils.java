package com.chat.server;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ServerUtils {

    public static void log(String message) {
        String timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
        System.out.println("[" + timestamp + "] " + message);
    }
}
