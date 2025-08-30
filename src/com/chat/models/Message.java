package com.chat.models;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Message model to encapsulate chat data.
 * Implements Serializable so it can be extended for file transfer.
 */
public class Message implements Serializable {

    private String sender;
    private String receiver;   // null or "all" for group chat
    private String content;
    private MessageType type;
    private String timestamp;

    public enum MessageType {
        CHAT,        // normal chat message
        PRIVATE,     // private message
        FILE,        // file transfer
        SYSTEM       // server/system notifications
    }

    public Message(String sender, String receiver, String content, MessageType type) {
        this.sender = sender;
        this.receiver = receiver;
        this.content = content;
        this.type = type;
        this.timestamp = new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    // Getters
    public String getSender() { return sender; }
    public String getReceiver() { return receiver; }
    public String getContent() { return content; }
    public MessageType getType() { return type; }
    public String getTimestamp() { return timestamp; }

    @Override
    public String toString() {
        if (type == MessageType.PRIVATE) {
            return "[PM][" + timestamp + "] " + sender + " -> " + receiver + ": " + content;
        } else if (type == MessageType.SYSTEM) {
            return "[SYSTEM][" + timestamp + "] " + content;
        }
        return "[" + timestamp + "] " + sender + ": " + content;
    }
}
