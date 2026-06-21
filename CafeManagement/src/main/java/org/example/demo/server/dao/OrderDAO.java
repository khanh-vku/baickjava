package org.example.demo.server.dao;

import org.example.demo.server.config.DBConnection;
import org.example.demo.model.Order;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public List<Order> findAll() {

        List<Order> list = new ArrayList<>();

        String sql = "SELECT * FROM Orders";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql);
                ResultSet rs = ps.executeQuery()
        ) {

            while (rs.next()) {

                Order o = new Order();

                o.setOrderId(rs.getInt("OrderID"));
                o.setOrderDate(
                        rs.getTimestamp("OrderDate")
                                .toLocalDateTime()
                );
                o.setEmployeeId(rs.getInt("EmployeeID"));
                o.setTotalAmount(rs.getDouble("TotalAmount"));

                list.add(o);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public Order findById(int orderId) {

        String sql =
                "SELECT * FROM Orders WHERE OrderID=?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {

                    Order o = new Order();

                    o.setOrderId(rs.getInt("OrderID"));
                    o.setOrderDate(
                            rs.getTimestamp("OrderDate")
                                    .toLocalDateTime()
                    );
                    o.setEmployeeId(rs.getInt("EmployeeID"));
                    o.setTotalAmount(rs.getDouble("TotalAmount"));

                    return o;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public int createOrder(Order o) {

        String sql = """
            INSERT INTO Orders(EmployeeID, TotalAmount)
            VALUES(?, ?)
            """;

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps =
                        con.prepareStatement(
                                sql,
                                Statement.RETURN_GENERATED_KEYS
                        )
        ) {

            ps.setInt(1, o.getEmployeeId());
            ps.setDouble(2, o.getTotalAmount());

            int affected = ps.executeUpdate();

            if (affected > 0) {

                try (ResultSet rs = ps.getGeneratedKeys()) {

                    if (rs.next()) {
                        return rs.getInt(1);
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return -1;
    }

    public boolean deleteOrder(int orderId) {

        String sql =
                "DELETE FROM Orders WHERE OrderID=?";

        try (
                Connection con = DBConnection.getConnection();
                PreparedStatement ps = con.prepareStatement(sql)
        ) {

            ps.setInt(1, orderId);

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
