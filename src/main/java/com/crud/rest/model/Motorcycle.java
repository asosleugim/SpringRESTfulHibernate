package com.crud.rest.model;

public class Motorcycle extends AbstractVehicle {

	/**
	 * Calculates the Motorcycle Price based on Model
	 */
	@Override
	public double calculatePrice(String model) {

		double price;
		switch (model) {

		case "Yamaha":
			price = 12000;
			break;

		case "Honda":
			price = 11000;
			break;

		case "Ducati":
			price = 13000;
			break;

		case "Kawasaki":
			price = 10500;
			break;

		default:
			price = 0.0;
		}

		return price;
	}
/**
 * Calculates the Motorcycle Speed based on Model
 * 
 */
	@Override
	public int calculateSpeed(String model) {
		int speed;
		switch (model) {

		case "Yamaha":
			speed = 70;
			break;

		case "Honda":
			speed = 55;
			break;

		case "Ducati":
			speed = 68;
			break;

		case "Kawasaki":
			speed = 65;
			break;

		default:
			speed = 0;
		}
		return speed;
	}
}
