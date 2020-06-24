package ru.job4j.design.lsp.parking.model;

import java.util.Objects;

public abstract class AbstractVehicle implements Vehicle {

    private String number;

    private String name;

    private int size;

    public AbstractVehicle(String number, String name, int size) {
        this.number = number;
        this.name = name;
        this.size = size;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public String number() {
        return this.number;
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AbstractVehicle that = (AbstractVehicle) o;
        return Objects.equals(number, that.number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }
}
