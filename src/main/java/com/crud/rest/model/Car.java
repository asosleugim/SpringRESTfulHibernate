package com.crud.rest.model;

public class Car extends AbstractVehicle {

/**
 * Calculates the Price of the Car based on Model
 */
	@Override
	public double calculatePrice(String model) {


		double price;
		switch (model) {

		case "Toyota":
			price = 46255;
			break;

		case "BMW":
			price = 39335;
			break;

		case "Mercedes-Benz":
			price = 35544;
			break;

		case "Nissan":
			price = 24768;
			break;

		default:
			price = 0.0;
		}

		return price;
	}
/**
 * Calculates the Car Speed based on Model
 */
	@Override
	public int calculateSpeed(String model) {
		int speed;
		switch (model) {

		case "Toyota":
			speed = 120;
			break;

		case "BMW":
			speed = 115;
			break;

		case "Mercedes-Benz":
			speed = 110;
			break;

		case "Nissan":
			speed = 100;
			break;

		default:
			speed = 0;
		}
		return speed;
	}
}
