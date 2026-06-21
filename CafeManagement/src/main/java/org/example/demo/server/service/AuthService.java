package org.example.demo.server.service;

import org.example.demo.server.dao.UserDAO;
import org.example.demo.model.User;

public class AuthService {

    private final UserDAO dao = new UserDAO();

    public User login(String username, String password) {

        User user = dao.login(username, password);

        if (user == null) {
            return null;
        }

        return user;
    }

    public boolean isAdmin(User user) {
        return user != null && "ADMIN".equalsIgnoreCase(user.getRole());
    }

    public boolean isStaff(User user) {
        return user != null && "STAFF".equalsIgnoreCase(user.getRole());
    }
}