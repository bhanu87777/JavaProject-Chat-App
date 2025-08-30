package com.chat.server;

import com.chat.utils.LoggerUtil;

import java.io.*;
import java.net.Socket;
import java.util.List;

public class ClientHandler implements Runnable {

    private Socket socket;
    private BufferedReader reader;
    private PrintWriter writer;
    private List<ClientHandler> clients;
    private String clientName;

    public ClientHandler(Socket socket, List<ClientHandler> clients) {
        this.socket = socket;
        this.clients = clients;

        try {
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);

            // Ask for name
            writer.println("Enter your name: ");
            this.clientName = reader.readLine();

            broadcast("[SERVER] " + clientName + " has joined the chat!");

        } catch (IOException e) {
            LoggerUtil.logError("[CLIENT-HANDLER] Error: " + e.getMessage());
        }
    }

    @Override
    public void run() {
        String message;
        try {
            while ((message = reader.readLine()) != null) {
                if (message.equalsIgnoreCase("exit")) {
                    broadcast("[SERVER] " + clientName + " has left the chat.");
                    clients.remove(this);
                    socket.close();
                    break;
                }
                broadcast(clientName + ": " + message);
            }
        } catch (IOException e) {
            LoggerUtil.logError("[CLIENT-HANDLER] Connection error: " + e.getMessage());
        }
    }

    // ✅ Add this method so ChatService can send messages to this client
    public void sendMessage(String message) {
        writer.println(message);
    }

    // ✅ Add this method so ChatService can get client's name
    public String getClientName() {
        return clientName;
    }

    // Optional helper to broadcast to all clients
    private void broadcast(String message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message);
        }
        LoggerUtil.logSystem(message);
    }
}
