package org.example.demo.model;

public class TopProduct {

    private String productName;
    private int sold;

    public TopProduct() {}

    public TopProduct(String productName, int sold) {
        this.productName = productName;
        this.sold = sold;
    }

    public String getProductName() {
        return productName;
    }

    public int getSold() {
        return sold;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }
}