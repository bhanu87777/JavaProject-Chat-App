package com.chat.services;

import com.chat.models.Message;
import com.chat.models.User;
import com.chat.server.ClientHandler;

import java.util.List;

/**
 * ChatService handles group messages, private messages, and system notifications.
 */
public class ChatService {

    private List<ClientHandler> clients;

    public ChatService(List<ClientHandler> clients) {
        this.clients = clients;
    }

    /** Broadcast message to everyone */
    public void broadcast(Message message) {
        for (ClientHandler client : clients) {
            client.sendMessage(message.toString());
        }
    }

    /** Send private message from one user to another */
    public void privateMessage(Message message) {
        boolean found = false;
        for (ClientHandler client : clients) {
            if (client.getClientName().equalsIgnoreCase(message.getReceiver())) {
                client.sendMessage(message.toString());
                found = true;
                break;
            }
        }
        if (!found) {
            // If receiver not found, notify sender
            for (ClientHandler client : clients) {
                if (client.getClientName().equalsIgnoreCase(message.getSender())) {
                    client.sendMessage("[SYSTEM] User " + message.getReceiver() + " not found!");
                    break;
                }
            }
        }
    }

    /** Remove disconnected client */
    public void removeClient(ClientHandler client) {
        clients.remove(client);
    }

    /** Send system-wide notification */
    public void systemMessage(String text) {
        Message sysMsg = new Message("SERVER", "all", text, Message.MessageType.SYSTEM);
        broadcast(sysMsg);
    }
}
