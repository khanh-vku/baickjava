package org.example.demo.client.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.example.demo.model.Employee;
import org.example.demo.server.service.EmployeeService;

import java.util.List;

public class EmployeeController {

    private final EmployeeService employeeService = new EmployeeService();

    @FXML private TextField txtId;
    @FXML private TextField txtName;
    @FXML private TextField txtGender;
    @FXML private TextField txtPhone;
    @FXML private TextField txtAddress;
    @FXML private TextField txtPosition;
    @FXML private TextField txtSalary;
    @FXML private TextField txtSearch;

    @FXML private TableView<Employee> tableEmployee;

    @FXML private TableColumn<Employee,Integer> colId;
    @FXML private TableColumn<Employee,String> colName;
    @FXML private TableColumn<Employee,String> colGender;
    @FXML private TableColumn<Employee,String> colPhone;
    @FXML private TableColumn<Employee,String> colAddress;
    @FXML private TableColumn<Employee,String> colPosition;
    @FXML private TableColumn<Employee,Double> colSalary;

    @FXML
    public void initialize() {

        colId.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getEmployeeId()));
        colName.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getFullName()));
        colGender.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getGender()));
        colPhone.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPhone()));
        colAddress.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getAddress()));
        colPosition.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getPosition()));
        colSalary.setCellValueFactory(data -> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getSalary()));

        tableEmployee.setOnMouseClicked(e -> selectEmployee());

        loadData();
    }

    private void loadData() {
        List<Employee> list = employeeService.getAllEmployees();
        tableEmployee.setItems(FXCollections.observableArrayList(list));
    }

    private void selectEmployee() {

        Employee emp = tableEmployee.getSelectionModel().getSelectedItem();
        if (emp == null) return;

        txtId.setText(String.valueOf(emp.getEmployeeId()));
        txtName.setText(emp.getFullName());
        txtGender.setText(emp.getGender());
        txtPhone.setText(emp.getPhone());
        txtAddress.setText(emp.getAddress());
        txtPosition.setText(emp.getPosition());
        txtSalary.setText(String.valueOf(emp.getSalary()));
    }

    @FXML
    private void addEmployee() {

        try {
            Employee emp = new Employee();

            emp.setFullName(txtName.getText());
            emp.setGender(txtGender.getText());
            emp.setPhone(txtPhone.getText());
            emp.setAddress(txtAddress.getText());
            emp.setPosition(txtPosition.getText());
            emp.setSalary(Double.parseDouble(txtSalary.getText()));

            boolean ok = employeeService.addEmployee(emp);

            showMessage(ok ? "Thêm thành công" : "Thêm thất bại");

            loadData();
            refreshForm();

        } catch (Exception e) {
            showMessage("Lỗi: " + e.getMessage());
        }
    }

    @FXML
    private void updateEmployee() {

        try {
            Employee emp = new Employee();

            emp.setEmployeeId(Integer.parseInt(txtId.getText()));
            emp.setFullName(txtName.getText());
            emp.setGender(txtGender.getText());
            emp.setPhone(txtPhone.getText());
            emp.setAddress(txtAddress.getText());
            emp.setPosition(txtPosition.getText());
            emp.setSalary(Double.parseDouble(txtSalary.getText()));

            boolean ok = employeeService.updateEmployee(emp);

            showMessage(ok ? "Cập nhật thành công" : "Cập nhật thất bại");

            loadData();
            refreshForm();

        } catch (Exception e) {
            showMessage("Lỗi: " + e.getMessage());
        }
    }

    @FXML
    private void deleteEmployee() {

        try {
            int id = Integer.parseInt(txtId.getText());

            boolean ok = employeeService.deleteEmployee(id);

            showMessage(ok ? "Xóa thành công" : "Xóa thất bại");

            loadData();
            refreshForm();

        } catch (Exception e) {
            showMessage("Lỗi: " + e.getMessage());
        }
    }

    @FXML
    private void refreshForm() {

        txtId.clear();
        txtName.clear();
        txtGender.clear();
        txtPhone.clear();
        txtAddress.clear();
        txtPosition.clear();
        txtSalary.clear();
        txtSearch.clear();

        tableEmployee.getSelectionModel().clearSelection();
    }

    @FXML
    private void searchEmployee() {

        String keyword = txtSearch.getText();

        List<Employee> list = employeeService.searchEmployees(keyword);

        tableEmployee.setItems(FXCollections.observableArrayList(list));
    }

    private void showMessage(String msg) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.show();
    }
}