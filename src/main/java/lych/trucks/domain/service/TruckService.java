package lych.trucks.domain.service;

import lych.trucks.domain.model.Truck;

public interface TruckService {

    Truck create(Integer driverId,Truck truck);

    Truck fetch(Integer id);

    void delete(Integer id);

    Truck update(Truck truck);
}
