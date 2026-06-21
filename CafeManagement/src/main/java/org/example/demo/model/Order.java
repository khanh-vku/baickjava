package org.example.demo.model;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Order implements Serializable {

    private int orderId;
    private LocalDateTime orderDate;
    private int employeeId;
    private double totalAmount;

    public Order() {
    }

    public Order(int orderId, LocalDateTime orderDate, int employeeId, double totalAmount) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.employeeId = employeeId;
        this.totalAmount = totalAmount;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}