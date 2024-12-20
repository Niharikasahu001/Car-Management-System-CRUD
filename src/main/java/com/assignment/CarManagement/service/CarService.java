package com.assignment.CarManagement.service;

import com.assignment.CarManagement.CarDto;
import com.assignment.CarManagement.dao.CarRepository;
import com.assignment.CarManagement.entities.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {
    @Autowired
    CarRepository carRepository;

    public long saveCar(CarDto carDto)
    {
        Car car = new Car(carDto);
        this.carRepository.save(car);
        return car.getId();
    }

    public List<Car> getAllCars()
    {
        return this.carRepository.findAll();
    }

    public Car updateCar(CarDto carDto, long id)
    {
        Optional<Car> optionalCar = this.carRepository.findById(id);
        if(optionalCar.isPresent())
        {
            Car car = optionalCar.get();
            car.setName(carDto.getName());
            car.setModel(carDto.getModel());
            car.setColor(carDto.getColor());
            car.setPrice(carDto.getPrice());
            car.setFuelType(carDto.getFuelType());
            car.setFuelType(carDto.getFuelType());

            Car updatedCar = this.carRepository.save(car);
            return updatedCar;
        }
        return new Car();
    }

    public Car getCarById(Long id)
    {
        Optional<Car> optional = carRepository.findById(id);
        Car car = null;
        if(optional.isPresent())
        {
            car = optional.get();
        }
        else {
            throw new RuntimeException("Car not found for id:: "+id);
        }
        return car;
    }

    public void deleteCarById(long id)
    {
        this.carRepository.deleteById(id);
    }

}
