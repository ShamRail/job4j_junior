package ru.job4j.design.lsp.parking;

import ru.job4j.design.lsp.parking.model.Vehicle;

public interface Parking {
    boolean add(Vehicle vehicle);
    Vehicle retrieve(String number);
    boolean canPark(Vehicle vehicle);
}
