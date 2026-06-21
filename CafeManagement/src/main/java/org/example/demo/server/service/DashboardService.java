package org.example.demo.server.service;

import org.example.demo.server.dao.DashboardDAO;
import org.example.demo.model.TopProduct;

import java.util.List;

public class DashboardService {

    private final DashboardDAO dao = new DashboardDAO();

    public int getEmployees() {
        return dao.getEmployeeCount();
    }

    public int getProducts() {
        return dao.getProductCount();
    }

    public double getRevenue() {
        return dao.getTotalRevenue();
    }

    public int getOrders() {
        return dao.getOrderCount();
    }

    public double getAverageOrder() {
        return dao.getAverageOrder();
    }

    public List<TopProduct> getTopProduct() {
        return dao.getTopProducts();
    }
}