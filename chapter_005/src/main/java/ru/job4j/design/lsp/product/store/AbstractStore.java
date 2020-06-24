package ru.job4j.design.lsp.product.store;

import ru.job4j.design.lsp.product.food.Food;

import java.util.LinkedList;
import java.util.List;

public abstract class AbstractStore implements Store {

    private List<Food> foods = new LinkedList<>();

    @Override
    public void add(Food food) {
        foods.add(food);
    }

    @Override
    public List<Food> clear() {
        List<Food> result = new LinkedList<>(foods);
        foods.clear();
        return result;
    }

}
