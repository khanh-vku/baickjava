package org.example.demo.server.socket;

import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {

    public static void main(String[] args) {

        try (ServerSocket server = new ServerSocket(9999)) {

            System.out.println(" Server started ...");

            while (true) {

                Socket socket = server.accept();

                System.out.println(" Client connected: " + socket.getInetAddress());

                ClientHandler handler = new ClientHandler(socket);

                new Thread(handler).start();
            }

        } catch (Exception e) {
            System.out.println(" Server error:");
            e.printStackTrace();
        }
    }
}