package lych.trucks.domain.service;

import lych.trucks.domain.model.Truck;

import java.util.List;

public interface TruckService {

    List<Truck> fetchAll();

    Truck create(Truck truck);

    Truck fetch(Integer id);

    void delete(Integer id);

    Truck update(Truck truck);
}
