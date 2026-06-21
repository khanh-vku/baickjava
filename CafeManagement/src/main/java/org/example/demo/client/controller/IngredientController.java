package org.example.demo.client.controller;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import org.example.demo.model.Ingredient;
import org.example.demo.server.service.IngredientService;

import java.util.List;

public class IngredientController {

    private final IngredientService service = new IngredientService();

    @FXML
    private TextField txtId;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtUnit;

    @FXML
    private TextField txtQuantity;

    @FXML
    private TextField txtImportPrice;

    @FXML
    private TextField txtSearch;

    @FXML
    private TableView<Ingredient> tableIngredient;

    @FXML
    private TableColumn<Ingredient, Integer> colId;

    @FXML
    private TableColumn<Ingredient, String> colName;

    @FXML
    private TableColumn<Ingredient, String> colUnit;

    @FXML
    private TableColumn<Ingredient, Integer> colQuantity;

    @FXML
    private TableColumn<Ingredient, Double> colImportPrice;

    @FXML
    public void initialize() {

        if (tableIngredient == null) {
            System.out.println("");
            return;
        }

        colId.setCellValueFactory(new PropertyValueFactory<>("ingredientId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("ingredientName"));
        colUnit.setCellValueFactory(new PropertyValueFactory<>("unit"));
        colQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
        colImportPrice.setCellValueFactory(new PropertyValueFactory<>("importPrice"));

        tableIngredient.setOnMouseClicked(e -> selectIngredient());

        loadData();
    }

    private void loadData() {

        List<Ingredient> list = service.getAll();

        tableIngredient.setItems(
                FXCollections.observableArrayList(list)
        );
    }

    private void selectIngredient() {

        Ingredient ingredient =
                tableIngredient.getSelectionModel().getSelectedItem();

        if (ingredient == null) return;

        txtId.setText(String.valueOf(ingredient.getIngredientId()));
        txtName.setText(ingredient.getIngredientName());
        txtUnit.setText(ingredient.getUnit());
        txtQuantity.setText(String.valueOf(ingredient.getQuantity()));
        txtImportPrice.setText(String.valueOf(ingredient.getImportPrice()));
    }

    @FXML
    private void addIngredient() {

        try {
            Ingredient i = new Ingredient();

            i.setIngredientName(txtName.getText());
            i.setUnit(txtUnit.getText());
            i.setQuantity(Integer.parseInt(txtQuantity.getText()));
            i.setImportPrice(Double.parseDouble(txtImportPrice.getText()));

            boolean ok = service.add(i);

            if (!ok) {
                showMessage("Thêm thất bại (có thể trùng hoặc lỗi DB)");
                return;
            }

            showMessage("Thêm thành công");
            loadData();
            refreshForm();

        } catch (Exception e) {
            showMessage("Lỗi input: " + e.getMessage());
        }
    }

    @FXML
    private void updateIngredient() {

        try {
            Ingredient i = new Ingredient();

            i.setIngredientId(Integer.parseInt(txtId.getText()));
            i.setIngredientName(txtName.getText());
            i.setUnit(txtUnit.getText());
            i.setQuantity(Integer.parseInt(txtQuantity.getText()));
            i.setImportPrice(Double.parseDouble(txtImportPrice.getText()));

            boolean ok = service.update(i);

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
    private void deleteIngredient() {

        try {
            int id = Integer.parseInt(txtId.getText());

            boolean ok = service.delete(id);

            if (!ok) {
                showMessage("Xóa thất bại");
                return;
            }

            showMessage("Xóa thành công");
            loadData();
            refreshForm();

        } catch (Exception e) {
            showMessage("Lỗi delete: " + e.getMessage());
        }
    }

    @FXML
    private void searchIngredient() {

        String keyword = txtSearch.getText();

        List<Ingredient> list = service.search(keyword);

        tableIngredient.setItems(
                FXCollections.observableArrayList(list)
        );
    }

    @FXML
    private void refreshForm() {

        txtId.clear();
        txtName.clear();
        txtUnit.clear();
        txtQuantity.clear();
        txtImportPrice.clear();
        txtSearch.clear();

        tableIngredient.getSelectionModel().clearSelection();
    }

    private void showMessage(String msg) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setContentText(msg);
        alert.show();
    }
}