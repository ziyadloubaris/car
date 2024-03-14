package com.example.car.entities;

import com.example.car.enums.FuelType;
import com.example.car.enums.Transmission;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
@Table
public class Car {
	@Id
	@GeneratedValue
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
