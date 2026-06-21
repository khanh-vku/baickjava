package org.example.demo.server.dao;

import org.example.demo.server.config.DBConnection;
import org.example.demo.model.Ingredient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class IngredientDAO {
    public List<Ingredient> findAll() {

        List<Ingredient> list = new ArrayList<>();

        String sql = "SELECT * FROM Ingredients";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Ingredient i = new Ingredient();

                i.setIngredientId(rs.getInt("IngredientID"));
                i.setIngredientName(rs.getString("IngredientName"));
                i.setUnit(rs.getString("Unit"));
                i.setQuantity(rs.getDouble("Quantity"));
                i.setImportPrice(rs.getDouble("ImportPrice"));

                list.add(i);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public Ingredient findById(int id) {

        String sql =
                "SELECT * FROM Ingredients WHERE IngredientID=?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Ingredient i = new Ingredient();

                    i.setIngredientId(rs.getInt("IngredientID"));
                    i.setIngredientName(rs.getString("IngredientName"));
                    i.setUnit(rs.getString("Unit"));
                    i.setQuantity(rs.getDouble("Quantity"));
                    i.setImportPrice(rs.getDouble("ImportPrice"));

                    return i;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean add(Ingredient i) {

        String sql =
                "INSERT INTO Ingredients " +
                        "(IngredientName, Unit, Quantity, ImportPrice) " +
                        "VALUES (?, ?, ?, ?)";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, i.getIngredientName());
            ps.setString(2, i.getUnit());
            ps.setDouble(3, i.getQuantity());
            ps.setDouble(4, i.getImportPrice());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(Ingredient i) {

        String sql =
                "UPDATE Ingredients " +
                        "SET IngredientName=?, Unit=?, Quantity=?, ImportPrice=? " +
                        "WHERE IngredientID=?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, i.getIngredientName());
            ps.setString(2, i.getUnit());
            ps.setDouble(3, i.getQuantity());
            ps.setDouble(4, i.getImportPrice());
            ps.setInt(5, i.getIngredientId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean delete(int id) {

        String sql =
                "DELETE FROM Ingredients WHERE IngredientID=?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public List<Ingredient> search(String keyword) {

        List<Ingredient> list = new ArrayList<>();

        String sql =
                "SELECT * FROM Ingredients " +
                        "WHERE IngredientName LIKE ? " +
                        "OR Unit LIKE ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            String value = "%" + keyword + "%";

            ps.setString(1, value);
            ps.setString(2, value);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Ingredient i = new Ingredient();

                    i.setIngredientId(rs.getInt("IngredientID"));
                    i.setIngredientName(rs.getString("IngredientName"));
                    i.setUnit(rs.getString("Unit"));
                    i.setQuantity(rs.getDouble("Quantity"));
                    i.setImportPrice(rs.getDouble("ImportPrice"));

                    list.add(i);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
