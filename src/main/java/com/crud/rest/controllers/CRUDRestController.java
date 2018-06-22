package com.crud.rest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.crud.rest.beans.Vehicle;
import com.crud.rest.service.VehicleService;

@RestController // combination of @Controller and @ResponseBody annotations
public class CRUDRestController {

	@Autowired
	private VehicleService vehicleService;

	// setter for vehicleService
	public void setvehicleService(VehicleService vehicleService) {
		this.vehicleService = vehicleService;
	}

	/**
	 * Insert Vehicle
	 * URL: http://localhost:8080/SpringRESTExample/vehicle/new
	 * JSON:
	 * {
		"year" : "2018",
		"model" : "Toyota",
		"type" : "Car"
		}
	 * @param vehicle
	 * @param ucb
	 * @return
	 */
	@RequestMapping(value = "/vehicle/new", method = RequestMethod.POST)
	public ResponseEntity<Void> addVehicle(@RequestBody Vehicle vehicle, UriComponentsBuilder ucb) {

		vehicleService.insertVehicle(vehicle);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}

	/**
	 * Get Single vehicle
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/vehicle/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Vehicle> getVehicle(@PathVariable("id") int id) {

		Vehicle vehicle = vehicleService.readVehicle(id);
		if (vehicle == null) {
			return new ResponseEntity<Vehicle>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);
	}

	/**
	 * Update vehicle
	 * 
	 * @param id
	 * @param v
	 * @return
	 */
	@RequestMapping(value = "/vehicle/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Vehicle> updateVehicle(@PathVariable("id") int id, @RequestBody Vehicle v) {

		Vehicle vehicle = vehicleService.readVehicle(id);

		if (vehicle == null) {
			return new ResponseEntity<Vehicle>(HttpStatus.NOT_FOUND);
		}
		vehicle = vehicleService.getVehicleDetails(vehicle);

		vehicleService.updateVehicle(vehicle);
		return new ResponseEntity<Vehicle>(vehicle, HttpStatus.OK);
	}

/**
 * Get all vehicles List
 * @return
 */
	@RequestMapping(value = "/vehicles", method = RequestMethod.GET)
	public ResponseEntity<List<Vehicle>> listAllVehicles() {
		List<Vehicle> vehicles = vehicleService.findAllVehicles();
		if (vehicles.isEmpty()) {
			return new ResponseEntity<List<Vehicle>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Vehicle>>(vehicles, HttpStatus.OK);
	}
	
	/**
	 * Delete Last Added Vehicle
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/vehicle/last", method = RequestMethod.DELETE)
	public ResponseEntity<Vehicle> deleteLastAddedVehicle() {

		Vehicle vehicle = vehicleService.findLastAddedVehicle();
		if (vehicle == null) {
			return new ResponseEntity<Vehicle>(HttpStatus.NOT_FOUND);
		}

		vehicleService.removeVehicle(vehicle);
		return new ResponseEntity<Vehicle>(HttpStatus.NO_CONTENT);
	}
	

	/**
	 * Delete vehicle by id
	 * 
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/vehicle/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Vehicle> deleteVehicle(@PathVariable("id") int id) {

		Vehicle vehicle = vehicleService.readVehicle(id);
		if (vehicle == null) {
			return new ResponseEntity<Vehicle>(HttpStatus.NOT_FOUND);
		}

		vehicleService.removeVehicle(vehicle);
		return new ResponseEntity<Vehicle>(HttpStatus.NO_CONTENT);
	}

	/**
	 * Delete All vehicles
	 * 
	 * @return
	 */
	@RequestMapping(value = "/vehicle/deleteall", method = RequestMethod.DELETE)
	public ResponseEntity<Vehicle> removeAllVehicles() {

		vehicleService.deleteAllVehicles();
		return new ResponseEntity<Vehicle>(HttpStatus.NO_CONTENT);
	}
}
