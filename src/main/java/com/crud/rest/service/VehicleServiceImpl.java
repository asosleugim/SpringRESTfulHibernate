package com.crud.rest.service;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.crud.rest.beans.Vehicle;
import com.crud.rest.dao.VehicleDao;
import com.crud.rest.model.AbstractVehicle;
import com.crud.rest.model.Airplane;
import com.crud.rest.model.Car;
import com.crud.rest.model.Motorcycle;
import com.crud.rest.model.Truck;

public class VehicleServiceImpl implements VehicleService {

	@Autowired
	private VehicleDao vehicleDao;

	// setter for vehicleDao
	public void setVehicleDao(VehicleDao vehicleDao) {
		this.vehicleDao = vehicleDao;
	}

	public Vehicle getVehicleDetails(Vehicle v) {

		String vehicleType = v.getType();
		AbstractVehicle vehicle = null;
		switch (vehicleType) {

		case "Car":
			vehicle = new Car();
			break;

		case "Truck":
			vehicle = new Truck();
			break;
		case "Motorcycle":
			vehicle = new Motorcycle();
			break;

		case "Airplane":
			vehicle = new Airplane();
			break;

		default:

		}

		double price = vehicle.calculatePrice(v.getModel());
		int speed = vehicle.calculateSpeed(v.getModel());

		v.setModel(v.getModel());
		v.setPrice(price);
		v.setSpeed(speed);
		v.setType(vehicleType);
		v.setYear(v.getYear());
		Date date = new Date(System.currentTimeMillis());
		date.setTime(System.currentTimeMillis());
		v.setDate(date);
		return v;

	}

	@Override
	public void insertVehicle(Vehicle v) {

		try {
			Vehicle vehicle = getVehicleDetails(v);
			vehicleDao.startTransaction();
			vehicleDao.persistData(vehicle);
			vehicleDao.commitTransaction();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			vehicleDao.closeConnection();
		}
	}

	public Vehicle readVehicle(int id) {
		Vehicle v = null;
		try {
			vehicleDao.startTransaction();
			v = (Vehicle) vehicleDao.readObject(Vehicle.class, id);
			vehicleDao.commitTransaction();

		} catch (Exception e) {
			System.out.println(e.getMessage());

		} finally {
			vehicleDao.closeConnection();
		}
		return v;
	}

	@Override
	public void updateVehicle(Vehicle v) {

		// update vehicle

		try {
			vehicleDao.startTransaction();
			vehicleDao.mergeObject(v);
			vehicleDao.commitTransaction();
		}

		catch (RuntimeException e) {
			vehicleDao.rollbackTransaction();
			System.out.println(e.getMessage());
		} finally {
			vehicleDao.closeConnection();
		}

	}

	@Override
	public void removeVehicle(Vehicle vehicle) {
		// delete vehicle
		try {
			vehicleDao.startTransaction();

			vehicleDao.removeObject(vehicle);
			vehicleDao.commitTransaction();
		}

		catch (RuntimeException e) {
			vehicleDao.rollbackTransaction();
			System.out.println(e.getMessage());
		} finally {
			vehicleDao.closeConnection();
		}

	}

	@SuppressWarnings("unchecked")
	public List<Vehicle> findAllVehicles() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();

		try {
			vehicleDao.startTransaction();
			vehicles = vehicleDao.createSelectQuery("From com.crud.rest.beans.Vehicle");
			vehicleDao.commitTransaction();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			vehicleDao.closeConnection();
		}
		return vehicles;
	}
	
	@SuppressWarnings("unchecked")
	public Vehicle findLastAddedVehicle() {
		List<Vehicle> vehicles = new ArrayList<Vehicle>();

		try {
			vehicleDao.startTransaction();
			vehicles = vehicleDao.createSelectQuery("From com.crud.rest.beans.Vehicle order by id desc");
			vehicleDao.commitTransaction();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			vehicleDao.closeConnection();
		}
		if (vehicles.size()>0)
			return vehicles.get(0);
		else 
			return null;
		}

	@Override
	public void deleteAllVehicles() {
		try {
			vehicleDao.startTransaction();
			vehicleDao.createDeleteQuery("delete from Vehicle");
			vehicleDao.commitTransaction();
		} catch (RuntimeException e) {
			vehicleDao.rollbackTransaction();
			System.out.println(e.getMessage());
		} finally {
			vehicleDao.closeConnection();
		}
	}

}