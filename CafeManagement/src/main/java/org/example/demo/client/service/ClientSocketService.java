package org.example.demo.client.service;

import java.io.*;
import java.net.Socket;

public class ClientSocketService {

    private static Socket socket;
    private static BufferedReader in;
    private static PrintWriter out;

    public static void connect() {
        try {
            if (socket != null && socket.isConnected()) return;

            socket = new Socket("localhost", 9999);

            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            out = new PrintWriter(socket.getOutputStream(), true);

            System.out.println("CONNECTED SERVER");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static synchronized String send(String request) {
        try {
            if (socket == null || socket.isClosed()) {
                connect();
            }

            out.println(request);
            out.flush();

            return in.readLine();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "FAIL|CLIENT_ERROR";
    }
}