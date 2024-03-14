package com.example.car.mapper;

import com.example.car.dto.CarDto;
import com.example.car.entities.Car;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CarMapper {

	@Mapping(target = "id", source = "id")
	@Mapping(target = "picture", ignore = true)
	CarDto mapToDto(Car car);

	@InheritInverseConfiguration
	@Mapping(target = "picture", ignore = true)
	Car mapDtoToCar(CarDto carDto);
}
