package org.example.demo.server.service;

import org.example.demo.server.dao.UserDAO;
import org.example.demo.model.User;

import java.util.List;

public class UserService {

    private final UserDAO dao = new UserDAO();

    public User login(String username, String password) {
        return dao.login(username, password);
    }

    public List<User> getAll() {
        return dao.findAll();
    }

    public boolean add(User user) {
        return dao.add(user);
    }

    public boolean update(User user) {
        return dao.update(user);
    }

    public boolean delete(int id) {
        return dao.delete(id);
    }

    public List<User> search(String keyword) {
        return dao.search(keyword);
    }
}