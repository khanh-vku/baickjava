package org.example.demo.server.security;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;

public class PasswordUtil {

    public static String hash(String password) {

        try {

            MessageDigest md = MessageDigest.getInstance("SHA-256");

            byte[] bytes = md.digest(
                    password.getBytes(StandardCharsets.UTF_8)
            );

            StringBuilder sb = new StringBuilder();

            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }

            return sb.toString();

        } catch (Exception e) {
            throw new RuntimeException("Error hashing password", e);
        }
    }

    public static boolean verify(String rawPassword, String hashedPassword) {

        return hash(rawPassword).equals(hashedPassword);
    }
}