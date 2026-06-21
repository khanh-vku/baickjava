package org.example.demo.server.dao;

import org.example.demo.server.config.DBConnection;
import org.example.demo.model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {

    public List<Product> findAll() {

        List<Product> list = new ArrayList<>();

        String sql = "SELECT * FROM Products";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Product p = new Product();

                p.setProductId(rs.getInt("ProductID"));
                p.setProductCode(rs.getString("ProductCode"));
                p.setProductName(rs.getString("ProductName"));
                p.setPrice(rs.getDouble("Price"));
                p.setCategory(rs.getString("Category"));
                p.setStock(rs.getInt("Stock"));

                list.add(p);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public boolean add(Product p) {

        String sql = """
                INSERT INTO Products
                (ProductCode,ProductName,Price,Category,Stock)
                VALUES(?,?,?,?,?)
                """;

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, p.getProductCode());
            ps.setString(2, p.getProductName());
            ps.setDouble(3, p.getPrice());
            ps.setString(4, p.getCategory());
            ps.setInt(5, p.getStock());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean update(Product p) {

        String sql = """
                UPDATE Products
                SET ProductCode=?,
                    ProductName=?,
                    Price=?,
                    Category=?,
                    Stock=?
                WHERE ProductID=?
                """;

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setString(1, p.getProductCode());
            ps.setString(2, p.getProductName());
            ps.setDouble(3, p.getPrice());
            ps.setString(4, p.getCategory());
            ps.setInt(5, p.getStock());
            ps.setInt(6, p.getProductId());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean updateStock(int productId, int newStock) {

        String sql = "UPDATE Products SET Stock=? WHERE ProductID=?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {
            ps.setInt(1, newStock);
            ps.setInt(2, productId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    public Product findById(int id) {
        String sql =
                "SELECT * FROM Products WHERE ProductID=?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Product p = new Product();

                    p.setProductId(rs.getInt("ProductID"));
                    p.setProductCode(rs.getString("ProductCode"));
                    p.setProductName(rs.getString("ProductName"));
                    p.setPrice(rs.getDouble("Price"));
                    p.setCategory(rs.getString("Category"));
                    p.setStock(rs.getInt("Stock"));

                    return p;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }


    public boolean delete(int productId) {

        String sql =
                "DELETE FROM Products WHERE ProductID=?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, productId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private int getCount(String sql) {

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    private double getDouble(String sql) {

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            if (rs.next()) {
                return rs.getDouble(1);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0.0;
    }

    public int getEmployeeCount() {
        return getCount("SELECT COUNT(*) FROM Employees");
    }

    public int getOrderCount() {
        return getCount("SELECT COUNT(*) FROM Orders");
    }

    public double getTotalRevenue() {
        return getDouble("SELECT SUM(TotalAmount) FROM Orders");
    }

    public double getAverageOrder() {
        return getDouble("SELECT AVG(TotalAmount) FROM Orders");
    }

    public List<Product> search(String keyword) {
        List<Product> list = new ArrayList<>();

        String sql =
                "SELECT * FROM Products " +
                        "WHERE ProductName LIKE ? " +
                        "OR ProductCode LIKE ? " +
                        "OR Category LIKE ?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            String value = "%" + keyword + "%";

            ps.setString(1, value);
            ps.setString(2, value);
            ps.setString(3, value);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    Product p = new Product();

                    p.setProductId(rs.getInt("ProductID"));
                    p.setProductCode(rs.getString("ProductCode"));
                    p.setProductName(rs.getString("ProductName"));
                    p.setPrice(rs.getDouble("Price"));
                    p.setCategory(rs.getString("Category"));
                    p.setStock(rs.getInt("Stock"));

                    list.add(p);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

}