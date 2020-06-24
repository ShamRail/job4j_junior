package ru.job4j.design.lsp.product.store;

import ru.job4j.design.lsp.product.food.Food;
import ru.job4j.design.lsp.product.util.DateUtil;

public class Trash extends AbstractStore {
    @Override
    public boolean assume(Food food) {
        return DateUtil.pastTimePercent(food.getCreateDate(), food.getExpiredDate()) == 100;
    }
}
