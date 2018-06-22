package com.crud.rest.service;

import java.util.List;

import com.crud.rest.beans.Vehicle;

public interface VehicleService {

	void insertVehicle(Vehicle v);

	Vehicle readVehicle(int id);

	void updateVehicle(Vehicle v);

	void removeVehicle(Vehicle v);

	List<Vehicle> findAllVehicles();

	void deleteAllVehicles();

	Vehicle getVehicleDetails(Vehicle v);

	public Vehicle findLastAddedVehicle();
}
