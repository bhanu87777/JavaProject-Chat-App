package com.chat.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class ClientListener implements Runnable {

    private Socket socket;
    private BufferedReader reader;

    public ClientListener(Socket socket, BufferedReader reader) {
        this.socket = socket;
        this.reader = reader;
    }

    @Override
    public void run() {
        String message;
        try {
            while ((message = reader.readLine()) != null) {
                ClientUtils.printMessage(message);
            }
        } catch (IOException e) {
            System.err.println("[CLIENT-LISTENER] Connection closed: " + e.getMessage());
        }
    }
}
