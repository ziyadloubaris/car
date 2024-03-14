package com.example.car.services;

import com.example.car.dto.CarDto;
import com.example.car.entities.Car;
import com.example.car.enums.FuelType;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public interface CarService {

	Car addCar(CarDto carDto, MultipartFile image) throws IOException;
	Page<Car> getCarsByTypeAndMaxPrice(FuelType fuelType,double price,int page,int size);

	@Transactional(readOnly = true)
	Set<String> getCarsMakes();

	void updateImage(Long carId, MultipartFile image) throws IOException;
}
