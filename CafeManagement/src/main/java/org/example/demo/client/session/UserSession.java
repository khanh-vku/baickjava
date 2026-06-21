package org.example.demo.client.session;

import org.example.demo.model.User;

public class UserSession {

    private static User currentUser;

    public static void setUser(User user) {
        currentUser = user;
    }

    public static User getUser() {
        return currentUser;
    }

    public static boolean isLogin() {
        return currentUser != null;
    }

    public static String getRole() {
        return currentUser == null ? null : currentUser.getRole();
    }

    public static boolean isAdmin() {
        return isLogin()
                && "ADMIN".equalsIgnoreCase(getRole());
    }

    public static boolean isStaff() {
        return isLogin()
                && "STAFF".equalsIgnoreCase(getRole());
    }

    public static void clear() {
        currentUser = null;
    }
}