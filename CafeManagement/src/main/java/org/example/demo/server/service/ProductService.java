package org.example.demo.server.service;

import org.example.demo.server.dao.ProductDAO;
import org.example.demo.model.Product;

import java.util.List;

public class ProductService {

    private final ProductDAO dao = new ProductDAO();

    public List<Product> getAll() {
        return dao.findAll();
    }

    public Product getById(int productId) {
        return dao.findById(productId);
    }

    public boolean add(Product product) {
        return dao.add(product);
    }

    public boolean update(Product product) {
        return dao.update(product);
    }

    public boolean delete(int productId) {
        return dao.delete(productId);
    }

    public List<Product> search(String keyword) {
        return dao.search(keyword);
    }

    public boolean updateStock(int productId, int newStock) {
        return dao.updateStock(productId, newStock);
    }
}