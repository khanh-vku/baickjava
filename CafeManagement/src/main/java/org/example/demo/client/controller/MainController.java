package org.example.demo.client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;

public class MainController {

    @FXML
    private BorderPane mainPane;

    @FXML
    private StackPane contentPane;

    @FXML
    public void initialize() {

        openDashboard();
    }

    @FXML
    private void openDashboard() {

        loadPage(
                "/org/example/demo/view/dashboard.fxml"
        );
    }

    @FXML
    private void openUser() {

        loadPage(
                "/org/example/demo/view/user.fxml"
        );
    }

    @FXML
    private void openEmployee() {

        loadPage(
                "/org/example/demo/view/employee.fxml"
        );
    }

    @FXML
    private void openProduct() {

        loadPage(
                "/org/example/demo/view/product.fxml"
        );
    }

    @FXML
    private void openIngredient() {

        loadPage(
                "/org/example/demo/view/ingredient.fxml"
        );
    }

    @FXML
    private void openOrder() {

        loadPage(
                "/org/example/demo/view/order.fxml"
        );
    }

    private void loadPage(String fxml) {

        try {

            Node node =
                    FXMLLoader.load(
                            getClass()
                                    .getResource(fxml)
                    );

            contentPane
                    .getChildren()
                    .setAll(node);

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


}
