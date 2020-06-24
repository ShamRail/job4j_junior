package ru.job4j.design.lsp.product;

import ru.job4j.design.lsp.product.food.Food;
import ru.job4j.design.lsp.product.store.Store;

import java.util.List;

public class ControlQuality {

    private List<Store> stores;

    public ControlQuality(List<Store> stores) {
        this.stores = stores;
    }

    public void distribute(Food food) {
        stores.stream().filter(s -> s.assume(food)).findFirst().ifPresent(s -> s.add(food));
    }

    public void distribute(List<Food> foods) {
        foods.forEach(this::distribute);
    }

}
