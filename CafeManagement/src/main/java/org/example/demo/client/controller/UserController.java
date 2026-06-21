package org.example.demo.client.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.demo.model.User;
import org.example.demo.server.service.UserService;

import java.util.List;

public class UserController {

    private final UserService service = new UserService();

    @FXML
    private TableView<User> tableUser;

    @FXML
    private TableColumn<User, Integer> colId;

    @FXML
    private TableColumn<User, String> colUsername;

    @FXML
    private TableColumn<User, String> colRole;

    @FXML
    private TextField txtUsername;

    @FXML
    private PasswordField txtPassword;

    @FXML
    private ComboBox<String> cboRole;

    @FXML
    private TextField txtEmployeeId;

    @FXML
    private TextField txtSearch;

    @FXML
    public void initialize() {

        try {
            colId.setCellValueFactory(new PropertyValueFactory<>("userId"));
            colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
            colRole.setCellValueFactory(new PropertyValueFactory<>("role"));

            cboRole.getItems().addAll("ADMIN", "STAFF");

            tableUser.setOnMouseClicked(e -> selectUser());

            loadData();

        } catch (Exception e) {
            showMessage("Init lỗi: " + e.getMessage());
        }
    }

    private void loadData() {
        try {
            List<User> list = service.getAll();

            if (list == null) {
                tableUser.setItems(FXCollections.observableArrayList());
                return;
            }

            tableUser.setItems(FXCollections.observableArrayList(list));

        } catch (Exception e) {
            showMessage("Load lỗi: " + e.getMessage());
        }
    }

    private void selectUser() {

        User user = tableUser.getSelectionModel().getSelectedItem();

        if (user == null) return;

        txtUsername.setText(user.getUsername());
        txtPassword.setText(user.getPasswordHash());
        cboRole.setValue(user.getRole());

        if (user.getEmployeeId() != null) {
            txtEmployeeId.setText(String.valueOf(user.getEmployeeId()));
        } else {
            txtEmployeeId.clear();
        }
    }

    @FXML
    private void addUser() {

        try {
            User user = new User();

            user.setUsername(txtUsername.getText());
            user.setPasswordHash(txtPassword.getText());
            user.setRole(cboRole.getValue());

            if (!txtEmployeeId.getText().isEmpty()) {
                user.setEmployeeId(Integer.parseInt(txtEmployeeId.getText()));
            }

            boolean ok = service.add(user);

            if (!ok) {
                showMessage("Thêm thất bại");
                return;
            }

            showMessage("Thêm thành công");
            loadData();
            refresh();

        } catch (Exception e) {
            showMessage("Lỗi add: " + e.getMessage());
        }
    }

    @FXML
    private void updateUser() {

        try {
            User user = tableUser.getSelectionModel().getSelectedItem();

            if (user == null) {
                showMessage("Chọn user trước!");
                return;
            }

            user.setUsername(txtUsername.getText());
            user.setPasswordHash(txtPassword.getText());
            user.setRole(cboRole.getValue());

            if (!txtEmployeeId.getText().isEmpty()) {
                user.setEmployeeId(Integer.parseInt(txtEmployeeId.getText()));
            }

            boolean ok = service.update(user);

            if (!ok) {
                showMessage("Update thất bại");
                return;
            }

            showMessage("Update thành công");
            loadData();

        } catch (Exception e) {
            showMessage("Lỗi update: " + e.getMessage());
        }
    }

    @FXML
    private void deleteUser() {

        try {
            User user = tableUser.getSelectionModel().getSelectedItem();

            if (user == null) {
                showMessage("Chọn user cần xóa!");
                return;
            }

            boolean ok = service.delete(user.getUserId());

            if (!ok) {
                showMessage("Xóa thất bại");
                return;
            }

            showMessage("Xóa thành công");
            loadData();
            refresh();

        } catch (Exception e) {
            showMessage("Lỗi delete: " + e.getMessage());
        }
    }

    @FXML
    private void searchUser() {

        try {
            List<User> list = service.search(txtSearch.getText());

            tableUser.setItems(FXCollections.observableArrayList(list));

        } catch (Exception e) {
            showMessage("Search lỗi: " + e.getMessage());
        }
    }

    private void refresh() {

        txtUsername.clear();
        txtPassword.clear();
        txtEmployeeId.clear();
        txtSearch.clear();
        cboRole.setValue(null);

        tableUser.getSelectionModel().clearSelection();
    }

    private void showMessage(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.show();
    }
}