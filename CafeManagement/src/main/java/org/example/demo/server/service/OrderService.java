package org.example.demo.server.service;

import org.example.demo.server.dao.OrderDAO;
import org.example.demo.model.Order;

import java.util.List;

public class OrderService {

    private final OrderDAO dao = new OrderDAO();

    public List<Order> getAllOrders() {
        return dao.findAll();
    }

    public Order getOrderById(int orderId) {
        return dao.findById(orderId);
    }

    public int createOrder(Order order) {
        return dao.createOrder(order);
    }

    public boolean deleteOrder(int orderId) {
        return dao.deleteOrder(orderId);
    }
}