package org.example.demo.server.socket;

import java.io.*;
import java.net.Socket;

public class ClientSocket {

    private static Socket socket;
    private static BufferedReader in;
    private static PrintWriter out;

    public static void connect() {

        try {

            if (socket != null && !socket.isClosed()) return;

            socket = new Socket("localhost", 9999);

            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            out = new PrintWriter(
                    socket.getOutputStream(),
                    true
            );

            System.out.println("Connected to server!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String send(String message) {

        try {

            if (socket == null || socket.isClosed()) {
                connect();
            }

            out.println(message);
            out.flush();

            return in.readLine();

        } catch (Exception e) {
            e.printStackTrace();
            return "FAIL Socket error";
        }
    }

    public static void close() {

        try {

            if (in != null) in.close();
            if (out != null) out.close();
            if (socket != null && !socket.isClosed()) socket.close();

            socket = null;
            in = null;
            out = null;

            System.out.println("Socket closed");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}