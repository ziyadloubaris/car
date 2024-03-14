package com.example.car.services;

import com.example.car.dto.CarDto;
import com.example.car.entities.Car;
import com.example.car.enums.FuelType;
import com.example.car.exceptions.CarNotFoundException;
import com.example.car.exceptions.CarRegistrationDateLimitException;
import com.example.car.mapper.CarMapper;
import com.example.car.repository.CarRepository;
import com.example.car.utils.Base64Encoder;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Set;

@Service
@AllArgsConstructor
@Slf4j
public class CarServiceImpl implements CarService {
	private final CarRepository carDao;
	private final CarMapper carMapper;
	@Value("${car.registration.limit}")
	private final LocalDateTime registrationDateLimit;

	@Override
	@Transactional
	public Car addCar(CarDto carDto, MultipartFile imageAsFile) throws IOException {
		if (carDto.getRegistrationDate() == null || carDto.getRegistrationDate().isBefore(registrationDateLimit)) {
			log.error("Registration date is before the limit.");
			throw new CarRegistrationDateLimitException("Only car registered after 2015 are allowed to be add to the catalog");
		}
		Car car = carMapper.mapDtoToCar(carDto);
		if (imageAsFile != null && !imageAsFile.isEmpty()) {
			String base64Image = Base64Encoder.encodeMultipartFileToBase64(imageAsFile);
			car.setPicture(base64Image);
		}
		log.info("Saving car");
		return carDao.save(car);
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Car> getCarsByTypeAndMaxPrice(FuelType fuelType, double price, int page, int size) {
		return carDao.findByFuelTypeAndPriceGreaterThanEqual(fuelType, price, PageRequest.of(page, size));
	}
	@Override
	@Transactional(readOnly = true)
	public Set<String> getCarsMakes() {
		return carDao.getAllMake();
	}

	@Override
	@Transactional
	public void updateImage(Long carId, MultipartFile imageAsFile) throws IOException {
		Car car = carDao.findById(carId).orElseThrow(()->new CarNotFoundException("this car not exist "+carId.toString()));
		car.setPicture(Base64Encoder.encodeMultipartFileToBase64(imageAsFile));
		carDao.save(car);
	}


}
