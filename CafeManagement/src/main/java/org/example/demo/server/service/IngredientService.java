package org.example.demo.server.service;

import org.example.demo.server.dao.IngredientDAO;
import org.example.demo.model.Ingredient;

import java.util.List;

public class IngredientService {

    private final IngredientDAO dao = new IngredientDAO();

    public List<Ingredient> getAll() {
        return dao.findAll();
    }

    public Ingredient findById(int id) {
        return dao.findById(id);
    }

    public boolean add(Ingredient ingredient) {
        return dao.add(ingredient);
    }

    public boolean update(Ingredient ingredient) {
        return dao.update(ingredient);
    }

    public boolean delete(int id) {
        return dao.delete(id);
    }

    public List<Ingredient> search(String keyword) {
        return dao.search(keyword);
    }
}