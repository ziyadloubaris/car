package com.example.car.controllers;

import com.example.car.dto.CarDto;
import com.example.car.entities.Car;
import com.example.car.enums.FuelType;
import com.example.car.services.CarService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Set;

@RestController
@RequestMapping("/cars")
@AllArgsConstructor
public class CarController {
	private final CarService carService;

	@PostMapping
	public ResponseEntity<Car> addCar(@RequestParam("carDto") CarDto carDto,
									  @RequestParam("image") MultipartFile image) throws IOException {
		Car car = carService.addCar(carDto, image);
		return ResponseEntity.status(HttpStatus.CREATED).body(car);
	}

	@GetMapping
	public ResponseEntity<Page<Car>> getCarsByTypeAndMaxPrice(@RequestParam FuelType fuelType,
															  @RequestParam double price,
															  @RequestParam int page,
															  @RequestParam int size) {
		Page<Car> cars = carService.getCarsByTypeAndMaxPrice(fuelType, price, page, size);
		return ResponseEntity.ok(cars);
	}

	@GetMapping("/makes")
	public ResponseEntity<Set<String>> getCarsMakes() {
		Set<String> carMakes = carService.getCarsMakes();
		return ResponseEntity.ok(carMakes);
	}

	@PutMapping("/{carId}/image")
	public ResponseEntity<Void> updateImage(@PathVariable Long carId,
											@RequestParam("image") MultipartFile imageAsFile) throws IOException {
		carService.updateImage(carId, imageAsFile);
		return ResponseEntity.ok().build();
	}
}
