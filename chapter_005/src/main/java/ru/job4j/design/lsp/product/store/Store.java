package ru.job4j.design.lsp.product.store;

import ru.job4j.design.lsp.product.food.Food;

import java.util.List;

public interface Store {
    boolean assume(Food food);
    void add(Food food);
    List<Food> clear();
}
