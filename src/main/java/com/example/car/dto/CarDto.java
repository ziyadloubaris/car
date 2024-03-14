package com.example.car.dto;

import com.example.car.enums.FuelType;
import com.example.car.enums.Transmission;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CarDto {
	private Long id;
	private String make;
	private String model;
	private LocalDateTime registrationDate;
	private Double price;
	private Double mileAge;
	private String picture;
	private FuelType fuelType;
	private Transmission transmission;
}
