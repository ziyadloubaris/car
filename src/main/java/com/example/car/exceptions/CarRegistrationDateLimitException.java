package com.example.car.exceptions;

public class CarRegistrationDateLimitException extends RuntimeException{
	public CarRegistrationDateLimitException(String msg){
		super(msg);
	}
}
