package ru.job4j.design.lsp.parking;

import org.hamcrest.core.Is;
import org.junit.Assert;
import org.junit.Test;
import ru.job4j.design.lsp.parking.model.Car;
import ru.job4j.design.lsp.parking.model.Truck;
import ru.job4j.design.lsp.parking.model.Vehicle;

public class SimpleParkingTest {

    @Test
    public void whenCanPark() {
        Parking parking = new SimpleParking();
        Vehicle car = new Car("AA102B", "Audi S7");
        Assert.assertTrue(parking.add(car));
        Assert.assertEquals(car, parking.retrieve(car.number()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenParkingFillAndParkCar() {
        Parking parking = new SimpleParking(1, 0);
        Vehicle car = new Car("AA102B", "Audi S7");
        Vehicle car2 = new Car("BB102C", "BMW X1");
        parking.add(car);
        parking.add(car2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void whenParkingFillAndParkTruck() {
        Parking parking = new SimpleParking(1, 1);
        Vehicle car = new Car("AA102B", "Audi S7");
        Vehicle truck1 = new Truck("BB102C", "Kamaz", 3);
        Vehicle truck2 = new Truck("BB102C", "Kamaz", 3);
        parking.add(car);
        parking.add(truck1);
        parking.add(truck2);
    }

    @Test
    public void whenRetrieve() {
        Parking parking = new SimpleParking(3, 0);
        Vehicle truck = new Truck("AA123C", "Kamaz", 3);
        parking.add(truck);
        Assert.assertThat(parking.retrieve(truck.number()), Is.is(truck));
    }

}
