package com.chat.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

    private static final String SERVER_HOST = "localhost"; // change if needed
    private static final int SERVER_PORT = 1234;

    public static void main(String[] args) {
        try {
            Socket socket = new Socket(SERVER_HOST, SERVER_PORT);
            System.out.println("=== Connected to Chat Server ===");

            BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);

            // First read: server will ask for name
            System.out.print(reader.readLine() + " ");
            String name = scanner.nextLine();
            writer.println(name);

            // Start a thread for listening to server messages
            ClientListener listener = new ClientListener(socket, reader);
            Thread listenerThread = new Thread(listener);
            listenerThread.start();

            // Sending messages
            System.out.println("Type messages (type 'exit' to quit):");
            String message;
            while (true) {
                message = scanner.nextLine();
                writer.println(message);
                if (message.equalsIgnoreCase("exit")) {
                    System.out.println("Disconnected from chat.");
                    socket.close();
                    break;
                }
            }

        } catch (IOException e) {
            System.err.println("[CLIENT] Error: " + e.getMessage());
        }
    }
}
