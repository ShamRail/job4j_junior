package ru.job4j.design.lsp.product.store;

import ru.job4j.design.lsp.product.food.Food;
import ru.job4j.design.lsp.product.util.DateUtil;

public class Warehouse extends AbstractStore {
    @Override
    public boolean assume(Food food) {
        int percent = DateUtil.pastTimePercent(food.getCreateDate(), food.getExpiredDate());
        return percent < 25;
    }
}
