package org.example.demo.server.dao;

import org.example.demo.server.config.DBConnection;
import org.example.demo.model.TopProduct;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DashboardDAO {
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

                double value = rs.getDouble(1);

                return rs.wasNull() ? 0 : value;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }

    public int getEmployeeCount() {
        return getCount(
                "SELECT COUNT(*) FROM Employees"
        );
    }

    public int getProductCount() {
        return getCount(
                "SELECT COUNT(*) FROM Products"
        );
    }

    public int getOrderCount() {
        return getCount(
                "SELECT COUNT(*) FROM Orders"
        );
    }

    public double getTotalRevenue() {
        return getDouble(
                "SELECT SUM(TotalAmount) FROM Orders"
        );
    }

    public double getAverageOrder() {
        return getDouble(
                "SELECT AVG(TotalAmount) FROM Orders"
        );
    }

    public List<TopProduct> getTopProducts() {

        List<TopProduct> list = new ArrayList<>();

        String sql = """
            SELECT TOP 5
                p.ProductName,
                SUM(od.Quantity) AS Sold
            FROM OrderDetails od
            INNER JOIN Products p
                ON od.ProductID = p.ProductID
            GROUP BY p.ProductName
            ORDER BY Sold DESC
            """;

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                TopProduct top = new TopProduct();

                top.setProductName(
                        rs.getString("ProductName")
                );

                top.setSold(
                        rs.getInt("Sold")
                );

                list.add(top);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
