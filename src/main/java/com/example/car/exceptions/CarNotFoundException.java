package com.example.car.exceptions;

public class CarNotFoundException extends RuntimeException {
	public CarNotFoundException(String msg) {
		super(msg);
	}
}
