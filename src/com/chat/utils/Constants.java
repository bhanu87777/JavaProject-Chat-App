package com.chat.utils;

/**
 * Constants used across the chat application.
 */
public class Constants {

    public static final int SERVER_PORT = 1234;          // Default server port
    public static final String SERVER_HOST = "localhost"; // Default server host

    public static final int BUFFER_SIZE = 4096;          // For file transfer
    public static final String FILE_SAVE_DIR = "downloads/"; // Directory to save incoming files

    private Constants() {
        // private constructor to prevent instantiation
    }
}
