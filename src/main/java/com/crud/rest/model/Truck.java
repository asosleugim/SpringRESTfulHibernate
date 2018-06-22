package com.crud.rest.model;

public class Truck extends AbstractVehicle {
/**
 * Calculates the Truck Price based on Model
 * 
 */
	@Override
	public double calculatePrice(String model) {

		double price;
		switch (model) {

		case "Chevrolet Silverado":
			price = 12000;
			break;

		case "GMC Canyon":
			price = 11000;
			break;

		case "Toyota Tundra":
			price = 13000;
			break;

		case "Honda Ridgeline":
			price = 10500;
			break;

		default:
			price = 0.0;
		}

		return price;
	}
/**
 * Calculates the Truck Speed based on Model
 * 
 */
	@Override
	public int calculateSpeed(String model) {
		int speed;
		
		switch (model) {
	case "Chevrolet Silverado":
		speed = 70;
		break;

	case "GMC Canyon":
		speed = 80;
		break;

	case "Toyota Tundra":
		speed = 75;
		break;

	case "Honda Ridgeline":
		speed = 68;
		break;

	default:
		speed = 0;
	}
		return speed;
	}
	
}
