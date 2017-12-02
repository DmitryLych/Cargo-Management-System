package lych.trucks.domain.service;

import lych.trucks.domain.model.Truck;

/**
 * Service for {@link Truck} work with database.
 */
public interface TruckService {

    /**
     * Method for create truck.
     *
     * @param driverId Driver driverId.
     * @param truck    Truck truck.
     * @return created truck.
     */
    Truck create(Integer driverId, Truck truck);

    /**
     * Method for find truck.
     *
     * @param driverId Driver driverId.
     * @return truck which found.
     */
    Truck fetch(Integer driverId);

    /**
     * Method for update truck.
     *
     * @param truck Truck truck.
     * @return updated truck.
     */
    Truck update(Truck truck);

    /**
     * Method for fetch truck by register sign.
     *
     * @param registerSign Truck registerSign.
     * @return truck which found.
     */
    Truck fetchByRegisterSign(String registerSign);

    /**
     * Method for fetch truck by body number.
     *
     * @param bodyNumber Truck bodyNumber.
     * @return truck which found.
     */
    Truck fetchByBodyNumber(String bodyNumber);
}
