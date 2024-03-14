package com.example.car.repository;

import com.example.car.entities.Car;
import com.example.car.enums.FuelType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
	Page<Car> findByFuelTypeAndPriceGreaterThanEqual(FuelType fuelType, Double price, Pageable pageable);

	@Query("select distinct c.make from Car c")
	Set<String> getAllMake();
}
