package org.example.demo.server.dao;

import org.example.demo.server.config.DBConnection;
import org.example.demo.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    public User login(String username, String password) {

        String sql =
                "SELECT * FROM Users WHERE Username=? AND PasswordHash=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    User u = new User();

                    u.setUserId(rs.getInt("UserID"));
                    u.setUsername(rs.getString("Username"));
                    u.setPasswordHash(rs.getString("PasswordHash"));
                    u.setRole(rs.getString("Role"));
                    u.setEmployeeId(rs.getInt("EmployeeID"));

                    return u;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public List<User> findAll() {

        List<User> list = new ArrayList<>();

        String sql = "SELECT * FROM Users";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                User u = new User();

                u.setUserId(rs.getInt("UserID"));
                u.setUsername(rs.getString("Username"));
                u.setPasswordHash(rs.getString("PasswordHash"));
                u.setRole(rs.getString("Role"));
                u.setEmployeeId(rs.getInt("EmployeeID"));

                list.add(u);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean add(User u) {

        String sql =
                "INSERT INTO Users(Username,PasswordHash,Role,EmployeeID) VALUES(?,?,?,?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPasswordHash());
            ps.setString(3, u.getRole());
            ps.setInt(4, u.getEmployeeId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(User u) {

        String sql =
                "UPDATE Users SET Username=?, PasswordHash=?, Role=?, EmployeeID=? WHERE UserID=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPasswordHash());
            ps.setString(3, u.getRole());
            ps.setInt(4, u.getEmployeeId());
            ps.setInt(5, u.getUserId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {

        String sql =
                "DELETE FROM Users WHERE UserID=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<User> search(String keyword) {

        List<User> list = new ArrayList<>();

        String sql =
                "SELECT * FROM Users WHERE Username LIKE ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, "%" + keyword + "%");

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    User u = new User();

                    u.setUserId(rs.getInt("UserID"));
                    u.setUsername(rs.getString("Username"));
                    u.setPasswordHash(rs.getString("Password"));
                    u.setRole(rs.getString("Role"));
                    u.setEmployeeId(rs.getInt("EmployeeID"));

                    list.add(u);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}
