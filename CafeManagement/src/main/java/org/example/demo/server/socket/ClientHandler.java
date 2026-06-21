package org.example.demo.server.socket;

import org.example.demo.server.service.AuthService;
import org.example.demo.model.User;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {

    private final Socket socket;
    private BufferedReader in;
    private PrintWriter out;

    private final AuthService authService = new AuthService();

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {

            in = new BufferedReader(
                    new InputStreamReader(socket.getInputStream())
            );

            out = new PrintWriter(
                    socket.getOutputStream(),
                    true
            );

            String request;

            while ((request = in.readLine()) != null) {

                System.out.println("REQUEST: " + request);

                String response = handle(request);

                out.println(response);
                out.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (socket != null) socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String handle(String request) {

        try {

            if (request == null || request.isEmpty()) {
                return "FAIL|Empty request";
            }

            String[] parts = request.split("\\|");
            String command = parts[0];

            switch (command) {

                case "LOGIN": {

                    if (parts.length < 3) {
                        return "FAIL|Invalid format";
                    }

                    String username = parts[1];
                    String password = parts[2];

                    User user = authService.login(username, password);

                    if (user == null) {
                        return "FAIL|Invalid login";
                    }

                    return "SUCCESS|"
                            + user.getUserId() + "|"
                            + user.getUsername() + "|"
                            + user.getRole() + "|"
                            + user.getEmployeeId();
                }

                default:
                    return "FAIL|Unknown command";
            }

        } catch (Exception e) {
            return "FAIL|" + e.getMessage();
        }
    }
}