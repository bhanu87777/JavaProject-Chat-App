package com.chat.client;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ClientUtils {

    public static void printMessage(String message) {
        String timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
        System.out.println("[" + timestamp + "] " + message);
    }
}
