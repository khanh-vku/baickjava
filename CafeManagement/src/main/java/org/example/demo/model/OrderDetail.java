package org.example.demo.model;

import java.io.Serializable;

public class OrderDetail implements Serializable {

    private int detailId;
    private int orderId;
    private int productId;
    private String productName;
    private int quantity;
    private double unitPrice;
    private double subTotal;

    public OrderDetail() {
    }

    public OrderDetail(int productId, String productName, int quantity, double unitPrice) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        recalc();
    }

    private void recalc() {
        this.subTotal = this.quantity * this.unitPrice;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
        recalc();
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
        recalc();
    }


    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }
}