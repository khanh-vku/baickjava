package org.example.demo.server.security;

import org.example.demo.client.session.UserSession;

public class ASEUtil {

    private static final String ADMIN = "ADMIN";
    private static final String STAFF = "STAFF";

    public static boolean isLogin() {
        return UserSession.getUser() != null;
    }

    public static boolean isAdmin() {

        return isLogin()
                && ADMIN.equalsIgnoreCase(UserSession.getRole());
    }

    public static boolean isStaff() {

        return isLogin()
                && STAFF.equalsIgnoreCase(UserSession.getRole());
    }

    public static boolean canAccessEmployee() {
        return isAdmin();
    }

    public static boolean canSell() {
        return isLogin();
    }

    public static boolean canAccessProduct() {
        return isLogin();
    }

    public static boolean canAccessIngredient() {
        return isAdmin();
    }
}