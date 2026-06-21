package org.example.demo.server.util;

import java.time.LocalDateTime;

public class LogUtil {

    public static void info(String msg) {
        System.out.println("[INFO] " + LocalDateTime.now() + " - " + msg);
    }

    public static void error(String msg) {
        System.err.println("[ERROR] " + LocalDateTime.now() + " - " + msg);
    }

    public static void warn(String msg) {
        System.out.println("[WARN] " + LocalDateTime.now() + " - " + msg);
    }
}