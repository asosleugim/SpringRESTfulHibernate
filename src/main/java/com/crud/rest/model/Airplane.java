package com.crud.rest.model;

public class Airplane extends AbstractVehicle {
/**
 * calculates the Price of the Airplane based on Model
 */
	@Override
	public double calculatePrice(String model) {

		double price;
		switch (model) {

		case "Emirates":
			price = 78000000;
			break;

		case "Delta":
			price = 6301000;
			break;

		case "British Airways":
			price = 6156000;
			break;

		case "United Airlines":
			price = 4621000;
			break;

		default:
			price = 0.0;
		}

		return price;
	}
	/**
	 * Calculates the speed of the airplane based on model
	 */

	@Override
	public int calculateSpeed(String model) {
		int speed;
		switch (model) {

		case "Emirates":
			speed = 575;
			break;

		case "Delta":
			speed = 545;
			break;

		case "British Airways":
			speed = 525;
			break;

		case "United Airlines":
			speed = 515;
			break;
		default:
			speed = 0;
		}
		return speed;
	}

}
