package org.example.demo.client.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import org.example.demo.model.TopProduct;
import org.example.demo.server.service.DashboardService;

import java.util.List;

public class DashboardController {

    private final DashboardService service = new DashboardService();

    @FXML private Label lblEmployees;
    @FXML private Label lblProducts;
    @FXML private Label lblRevenue;

    @FXML private Label lblTotalRevenue;
    @FXML private Label lblTotalOrders;
    @FXML private Label lblAvgOrder;

    @FXML private TableView<TopProduct> tableTopProduct;

    @FXML private TableColumn<TopProduct, String> colName;
    @FXML private TableColumn<TopProduct, Integer> colSold;

    @FXML
    public void initialize() {

        setupTable();
        loadDashboard();
        loadTopProducts();
    }

    private void setupTable() {

        colName.setCellValueFactory(
                new PropertyValueFactory<>("productName")
        );

        colSold.setCellValueFactory(
                new PropertyValueFactory<>("sold")
        );
    }

    private void loadDashboard() {

        try {
            lblEmployees.setText(String.valueOf(service.getEmployees()));
            lblProducts.setText(String.valueOf(service.getProducts()));
            lblRevenue.setText(String.valueOf(service.getRevenue()));

            lblTotalRevenue.setText(String.valueOf(service.getRevenue()));
            lblTotalOrders.setText(String.valueOf(service.getOrders()));

            int orders = service.getOrders();
            double revenue = service.getRevenue();

            double avg = orders == 0 ? 0 : revenue / orders;

            lblAvgOrder.setText(String.format("%.2f", avg));

        } catch (Exception e) {

            lblEmployees.setText("0");
            lblProducts.setText("0");
            lblRevenue.setText("0");

            lblTotalRevenue.setText("0");
            lblTotalOrders.setText("0");
            lblAvgOrder.setText("0");
        }
    }

    private void loadTopProducts() {

        try {
            List<TopProduct> list = service.getTopProduct();

            tableTopProduct.setItems(
                    FXCollections.observableArrayList(list)
            );

        } catch (Exception e) {

            tableTopProduct.setItems(
                    FXCollections.observableArrayList()
            );
        }
    }
}