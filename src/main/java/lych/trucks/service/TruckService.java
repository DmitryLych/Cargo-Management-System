package lych.trucks.service;

import lych.trucks.model.Truck;

import java.util.List;

public interface TruckService {
    List<Truck> trucks();

    Truck create(Truck truck);

    Truck fetch(Integer id);

    void delete(Integer id);

    Truck update(Truck truck);
}
