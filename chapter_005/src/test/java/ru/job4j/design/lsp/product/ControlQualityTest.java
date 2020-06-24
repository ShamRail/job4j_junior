package ru.job4j.design.lsp.product;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.design.lsp.product.food.Food;
import ru.job4j.design.lsp.product.food.Milk;
import ru.job4j.design.lsp.product.store.Shop;
import ru.job4j.design.lsp.product.store.Store;
import ru.job4j.design.lsp.product.store.Trash;
import ru.job4j.design.lsp.product.store.Warehouse;

import java.time.LocalDateTime;
import java.util.List;

public class ControlQualityTest {

    @Test
    public void whenAddToWarehouse() {
        Food food = new Milk();
        food.setCreateDate(LocalDateTime.now().plusDays(1));
        food.setExpiredDate(LocalDateTime.now().plusDays(10));
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(warehouse, shop, trash));
        controlQuality.distribute(food);
        Assert.assertThat(shop.clear().size(), Is.is(0));
        Assert.assertThat(trash.clear().size(), Is.is(0));
        Assert.assertThat(warehouse.clear().get(0).getName(), Is.is("milk"));
    }

    @Test
    public void whenAddToShopWithoutDiscount() {
        Food food = new Milk();
        food.setCreateDate(LocalDateTime.now().minusDays(5));
        food.setExpiredDate(LocalDateTime.now().plusDays(10));
        food.setDiscount(0);
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(warehouse, shop, trash));
        controlQuality.distribute(food);
        Food out = shop.clear().get(0);
        Assert.assertThat(out.getName(), Is.is("milk"));
        Assert.assertThat(out.getDiscount(), Is.is(0f));
        Assert.assertThat(trash.clear().size(), Is.is(0));
        Assert.assertThat(warehouse.clear().size(), Is.is(0));
    }

    @Test
    public void whenAddToShopWithDiscount() {
        Food food = new Milk();
        food.setCreateDate(LocalDateTime.now().minusDays(35));
        food.setExpiredDate(LocalDateTime.now().plusDays(10));
        food.setDiscount(0);
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(warehouse, shop, trash));
        controlQuality.distribute(food);
        Food out = shop.clear().get(0);
        Assert.assertThat(out.getName(), Is.is("milk"));
        Assert.assertThat(out.getDiscount(), Is.is(30f));
        Assert.assertThat(trash.clear().size(), Is.is(0));
        Assert.assertThat(warehouse.clear().size(), Is.is(0));
    }

    @Test
    public void whenAddToTrash() {
        Food food = new Milk();
        food.setCreateDate(LocalDateTime.now().minusDays(35));
        food.setExpiredDate(LocalDateTime.now().minusDays(1));
        Store warehouse = new Warehouse();
        Store shop = new Shop();
        Store trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(List.of(warehouse, shop, trash));
        controlQuality.distribute(food);
        Food out = trash.clear().get(0);
        Assert.assertThat(out.getName(), Is.is("milk"));
        Assert.assertThat(shop.clear().size(), Is.is(0));
        Assert.assertThat(warehouse.clear().size(), Is.is(0));
    }

}