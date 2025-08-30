package com.chat.services;

import com.chat.models.Message;

import java.io.*;
import java.net.Socket;

/**
 * Handles file transfer between clients.
 * This is a bonus feature and can be extended for bigger projects.
 */
public class FileTransferService {

    /** Send file over socket */
    public void sendFile(Socket socket, File file) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(socket.getOutputStream());
        FileInputStream fis = new FileInputStream(file);

        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = fis.read(buffer)) != -1) {
            bos.write(buffer, 0, bytesRead);
        }
        bos.flush();
        fis.close();
        System.out.println("[FILE] Sent: " + file.getName());
    }

    /** Receive file from socket */
    public void receiveFile(Socket socket, String savePath) throws IOException {
        BufferedInputStream bis = new BufferedInputStream(socket.getInputStream());
        FileOutputStream fos = new FileOutputStream(savePath);

        byte[] buffer = new byte[4096];
        int bytesRead;
        while ((bytesRead = bis.read(buffer)) != -1) {
            fos.write(buffer, 0, bytesRead);
        }
        fos.flush();
        fos.close();
        System.out.println("[FILE] Received: " + savePath);
    }

    /** Wrap file transfer as a message (for future extensibility) */
    public Message createFileMessage(String sender, String receiver, String fileName) {
        return new Message(sender, receiver, "[FILE] " + fileName, Message.MessageType.FILE);
    }
}
