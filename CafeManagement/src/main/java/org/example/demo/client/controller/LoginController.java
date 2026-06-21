package org.example.demo.client.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.example.demo.client.dto.Request;
import org.example.demo.client.service.ClientSocketService;

public class LoginController {

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private void login() {

        try {

            String data =
                    txtUsername.getText()
                            + "|"
                            + txtPassword.getText();

            String response =
                    ClientSocketService.send(
                            new Request("LOGIN", data).toString()
                    );

            System.out.println("SERVER: " + response);

            if (response.startsWith("SUCCESS")) {

                FXMLLoader loader = new FXMLLoader(
                        getClass().getResource(
                                "/org/example/demo/view/main.fxml"
                        )
                );

                if (loader.getLocation() == null) {
                    System.out.println(" main.fxml NOT FOUND");
                    return;
                }

                Parent root = loader.load();

                Stage stage = (Stage) txtUsername.getScene().getWindow();

                stage.setScene(new Scene(root));
                stage.show();

            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setContentText("Sai tài khoản hoặc mật khẩu");
                alert.show();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}