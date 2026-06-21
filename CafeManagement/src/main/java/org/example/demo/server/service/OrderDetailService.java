package org.example.demo.server.service;

import org.example.demo.server.dao.OrderDetailDAO;
import org.example.demo.model.OrderDetail;

public class OrderDetailService {

    private final OrderDetailDAO dao = new OrderDetailDAO();

    public boolean addDetail(OrderDetail detail) {
        return dao.add(detail);
    }
}