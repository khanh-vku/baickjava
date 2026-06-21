package org.example.demo.server.dao;

import org.example.demo.server.config.DBConnection;
import org.example.demo.model.OrderDetail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailDAO {

    public boolean add(OrderDetail d) {

        String sql = """
            INSERT INTO OrderDetails
            (OrderID, ProductID, Quantity, UnitPrice, SubTotal)
            VALUES (?, ?, ?, ?, ?)
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, d.getOrderId());
            ps.setInt(2, d.getProductId());
            ps.setInt(3, d.getQuantity());
            ps.setDouble(4, d.getUnitPrice());
            ps.setDouble(5, d.getSubTotal());

            return ps.executeUpdate() > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<OrderDetail> findByOrderId(int orderId) {

        List<OrderDetail> list = new ArrayList<>();

        String sql = """
            SELECT DetailID, OrderID, ProductID, Quantity, UnitPrice, SubTotal
            FROM OrderDetails
            WHERE OrderID = ?
        """;

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, orderId);

            try (ResultSet rs = ps.executeQuery()) {

                while (rs.next()) {

                    OrderDetail d = new OrderDetail();

                    d.setDetailId(rs.getInt("DetailID"));
                    d.setOrderId(rs.getInt("OrderID"));
                    d.setProductId(rs.getInt("ProductID"));

                    d.setQuantity(rs.getInt("Quantity"));
                    d.setUnitPrice(rs.getDouble("UnitPrice"));

                    d.setSubTotal(rs.getDouble("SubTotal"));

                    list.add(d);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }
}
