package ait.chat.server.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.security.spec.ECField;
import java.util.concurrent.BlockingQueue;

public class ChatServerReceiver implements Runnable{
    private final Socket socket;
    private final BlockingQueue<String> messageBox;

    public ChatServerReceiver(Socket socket, BlockingQueue<String> messageBox) {
        this.socket = socket;
        this.messageBox = messageBox;
    }

    @Override
    public void run() {
        try {
            BufferedReader socketReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            while (true) {
                String message = socketReader.readLine();
                if (message == null) {
                    break;
                }
                messageBox.put(message);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
