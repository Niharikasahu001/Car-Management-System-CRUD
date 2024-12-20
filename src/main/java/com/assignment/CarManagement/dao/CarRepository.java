package com.assignment.CarManagement.dao;

import com.assignment.CarManagement.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAll();
    List<Car> findByNameContaining(String name);
    List<Car> findByModelContaining(String model);
    List<Car> findByYear(int year);


}
