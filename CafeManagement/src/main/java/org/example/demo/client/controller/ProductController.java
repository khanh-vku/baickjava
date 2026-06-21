package org.example.demo.client.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.demo.model.Product;
import org.example.demo.server.service.ProductService;

import java.util.List;

public class ProductController {

    private final ProductService service = new ProductService();

    @FXML private TextField txtId;
    @FXML private TextField txtCode;
    @FXML private TextField txtName;
    @FXML private TextField txtPrice;
    @FXML private TextField txtCategory;
    @FXML private TextField txtStock;
    @FXML private TextField txtSearch;

    @FXML private TableView<Product> tableProduct;

    @FXML private TableColumn<Product, Integer> colId;
    @FXML private TableColumn<Product, String> colCode;
    @FXML private TableColumn<Product, String> colName;
    @FXML private TableColumn<Product, Double> colPrice;
    @FXML private TableColumn<Product, String> colCategory;
    @FXML private TableColumn<Product, Integer> colStock;

    @FXML
    public void initialize() {

        try {
            colId.setCellValueFactory(new PropertyValueFactory<>("productId"));
            colCode.setCellValueFactory(new PropertyValueFactory<>("productCode"));
            colName.setCellValueFactory(new PropertyValueFactory<>("productName"));
            colPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
            colStock.setCellValueFactory(new PropertyValueFactory<>("stock"));

            tableProduct.setOnMouseClicked(e -> {
                if (e.getClickCount() == 1) {
                    selectProduct();
                }
            });

            loadData();

        } catch (Exception e) {
            e.printStackTrace();
            showMessage("Lỗi khởi tạo ProductController");
        }
    }

    private void loadData() {
        try {
            List<Product> list = service.getAll();

            if (list == null) {
                tableProduct.setItems(FXCollections.observableArrayList());
                return;
            }

            tableProduct.setItems(FXCollections.observableArrayList(list));

        } catch (Exception e) {
            e.printStackTrace();
            showMessage("Không load được dữ liệu sản phẩm");
        }
    }

    private void selectProduct() {

        Product p = tableProduct.getSelectionModel().getSelectedItem();

        if (p == null) return;

        txtId.setText(String.valueOf(p.getProductId()));
        txtCode.setText(p.getProductCode());
        txtName.setText(p.getProductName());
        txtPrice.setText(String.valueOf(p.getPrice()));
        txtCategory.setText(p.getCategory());
        txtStock.setText(String.valueOf(p.getStock()));
    }

    @FXML
    private void addProduct() {

        try {
            Product p = new Product();

            p.setProductCode(txtCode.getText());
            p.setProductName(txtName.getText());
            p.setPrice(Double.parseDouble(txtPrice.getText()));
            p.setCategory(txtCategory.getText());
            p.setStock(Integer.parseInt(txtStock.getText()));

            boolean success = service.add(p);

            if (!success) {
                showMessage("Mã sản phẩm đã tồn tại!");
                return;
            }

            showMessage("Thêm sản phẩm thành công");
            loadData();
            refreshForm();

        } catch (NumberFormatException e) {
            showMessage("Giá hoặc tồn kho không hợp lệ!");
        }
    }

    @FXML
    private void updateProduct() {
        try {
            Product p = new Product();

            p.setProductId(Integer.parseInt(txtId.getText()));
            p.setProductCode(txtCode.getText());
            p.setProductName(txtName.getText());
            p.setPrice(Double.parseDouble(txtPrice.getText()));
            p.setCategory(txtCategory.getText());
            p.setStock(Integer.parseInt(txtStock.getText()));

            service.update(p);

            loadData();
            showMessage("Cập nhật thành công");

        } catch (Exception e) {
            e.printStackTrace();
            showMessage("Lỗi cập nhật");
        }
    }

    @FXML
    private void deleteProduct() {
        try {
            int id = Integer.parseInt(txtId.getText());

            service.delete(id);

            loadData();
            refreshForm();

            showMessage("Xóa thành công");

        } catch (Exception e) {
            e.printStackTrace();
            showMessage("Lỗi xóa sản phẩm");
        }
    }

    @FXML
    private void refreshForm() {

        txtId.clear();
        txtCode.clear();
        txtName.clear();
        txtPrice.clear();
        txtCategory.clear();
        txtStock.clear();
        txtSearch.clear();

        tableProduct.getSelectionModel().clearSelection();
    }

    @FXML
    private void searchProduct() {

        try {
            String keyword = txtSearch.getText();

            List<Product> list = service.search(keyword);

            tableProduct.setItems(FXCollections.observableArrayList(list));

        } catch (Exception e) {
            e.printStackTrace();
            showMessage("Lỗi tìm kiếm");
        }
    }

    private void showMessage(String msg) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Thông báo");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.show();
    }
}