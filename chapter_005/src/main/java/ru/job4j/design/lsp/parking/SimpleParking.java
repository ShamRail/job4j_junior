package ru.job4j.design.lsp.parking;

import ru.job4j.design.lsp.parking.model.Vehicle;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class SimpleParking implements Parking {

    private static final int DEFAULT_CAR_PLACES = 10;

    private static final int DEFAULT_TRUCK_PLACES = 5;

    private static final int CAR_SIZE = 1;

    private final int carPlaces;

    private final int truckPlaces;

    private List<Vehicle> cars = new ArrayList<>();

    private List<Vehicle> trucks = new ArrayList<>();

    public SimpleParking() {
        this.carPlaces = DEFAULT_CAR_PLACES;
        this.truckPlaces = DEFAULT_TRUCK_PLACES;
    }

    public SimpleParking(int carPlaces, int truckPlaces) {
        this.carPlaces = carPlaces;
        this.truckPlaces = truckPlaces;
    }

    @Override
    public boolean add(Vehicle vehicle) {
        if (!canPark(vehicle)) {
            throw new IllegalArgumentException();
        }
        if (vehicle.size() == CAR_SIZE) {
            cars.add(vehicle);
        } else {
            if (trucks.size() < truckPlaces) {
                trucks.add(vehicle);
            } else {
                IntStream.range(0, vehicle.size()).forEach(n -> cars.add(vehicle));
            }
        }
        return true;
    }

    @Override
    public Vehicle retrieve(String number) {
        List<Vehicle> carVehicles = cars.stream().filter(c -> c.number().equals(number)).collect(Collectors.toList());
        if (carVehicles.size() > 0 && cars.removeAll(carVehicles)) {
            return carVehicles.get(0);
        }
        List<Vehicle> truckVehicle = trucks.stream().filter(t -> t.number().equals(number)).collect(Collectors.toList());
        if (truckVehicle.size() > 0 && trucks.removeAll(truckVehicle)) {
            return truckVehicle.get(0);
        }
        return null;
    }

    @Override
    public boolean canPark(Vehicle vehicle) {
        if (vehicle.size() == CAR_SIZE && cars.size() == carPlaces) {
            return false;
        }
        if (vehicle.size() != CAR_SIZE && (trucks.size() == truckPlaces && vehicle.size() > (carPlaces - cars.size()))) {
            return false;
        }
        return true;
    }
}
