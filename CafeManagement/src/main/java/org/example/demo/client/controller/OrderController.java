package org.example.demo.client.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.demo.model.OrderDetail;
import org.example.demo.model.Product;
import org.example.demo.server.service.ProductService;

import java.util.List;

public class OrderController {

    private final ProductService productService = new ProductService();

    @FXML
    private TableView<Product> tableProduct;

    @FXML
    private TableView<OrderDetail> tableCart;

    @FXML private TableColumn<Product, Integer> colProductId;
    @FXML private TableColumn<Product, String> colProductName;
    @FXML private TableColumn<Product, Double> colProductPrice;
    @FXML private TableColumn<Product, Integer> colProductStock;

    @FXML
    private TableColumn<OrderDetail, String> colCartName;

    @FXML
    private TableColumn<OrderDetail, Integer> colCartQty;

    @FXML
    private TableColumn<OrderDetail, Double> colCartPrice;

    @FXML
    private TableColumn<OrderDetail, Double> colCartTotal;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtSearch;

    @FXML
    private Label lblTotal;

    private final ObservableList<OrderDetail> cartList = FXCollections.observableArrayList();

    private double totalAmount = 0;

    @FXML
    public void initialize() {

        colProductId.setCellValueFactory(new PropertyValueFactory<>("productId"));
        colProductName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colProductPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colProductStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

        colCartName.setCellValueFactory(new PropertyValueFactory<>("productName"));
        colCartQty.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colCartPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        colCartTotal.setCellValueFactory(new PropertyValueFactory<>("subTotal"));

        tableCart.setItems(cartList);

        loadProducts();
        lblTotal.setText(String.format("%,.0f", totalAmount));
    }

    private void loadProducts() {

        List<Product> list = productService.getAll();

        tableProduct.setItems(
                FXCollections.observableArrayList(list)
        );
    }

    @FXML
    private void addToCart() {

        Product product = tableProduct.getSelectionModel().getSelectedItem();

        if (product == null) {
            showMessage("Chọn sản phẩm!");
            return;
        }

        for (OrderDetail d : cartList) {
            if (d.getProductId() == product.getProductId()) {
                d.setQuantity(d.getQuantity() + 1);
                tableCart.refresh();
                calculateTotal();
                return;
            }
        }

        OrderDetail detail = new OrderDetail(
                product.getProductId(),
                product.getProductName(),
                1,
                product.getPrice()
        );

        cartList.add(detail);

        calculateTotal();
    }

    @FXML
    private void removeCartItem() {

        OrderDetail detail = tableCart.getSelectionModel().getSelectedItem();

        if (detail == null) return;

        cartList.remove(detail);

        calculateTotal();
    }

    @FXML
    private void searchProduct() {

        String keyword = txtSearch.getText();

        List<Product> list = productService.search(keyword);

        tableProduct.setItems(
                FXCollections.observableArrayList(list)
        );
    }

    @FXML
    private void checkout() {

        if (cartList.isEmpty()) {
            showMessage("Giỏ hàng trống!");
            return;
        }

        if (txtEmployeeId.getText().isEmpty()) {
            showMessage("Nhập mã nhân viên!");
            return;
        }

        showMessage("Thanh toán thành công!");

        cartList.clear();
        calculateTotal();
    }

    private void calculateTotal() {

        totalAmount = 0;

        for (OrderDetail d : cartList) {
            totalAmount += d.getSubTotal();
        }

        lblTotal.setText(String.format("%.0f", totalAmount));
    }

    private void showMessage(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.show();
    }
}