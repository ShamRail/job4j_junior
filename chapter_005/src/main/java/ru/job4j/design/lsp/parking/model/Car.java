package ru.job4j.design.lsp.parking.model;

public class Car extends AbstractVehicle {
    public Car(String number, String name) {
        super(number, name, 1);
    }
}
