package com.assignment.CarManagement.entities;

import com.assignment.CarManagement.CarDto;
import jakarta.persistence.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="car_name")
    @NotBlank(message="Car name is required")
    private String name;

    @NotBlank(message="Model is required")
    private String model;

    @Column(name="car_year")
    @Min(value=1996, message = "Year must be later than 1996")
    private int year;

    @Min(value=0, message="Price must be non-negative")
    private double price;

    @NotBlank(message="Color is required")
    private String color;

    @NotBlank(message="Fuel type is required")
    private String fuelType;

    public Car() {
    }

    public Car(Long id, String name, String model, int year, double price, String color, String fuelType) {
        this.id = id;
        this.name = name;
        this.model = model;
        this.year = year;
        this.price = price;
        this.color = color;
        this.fuelType = fuelType;
    }

    public Car(CarDto carDto) {
        this.name = carDto.getName();
        this.model = carDto.getModel();
        this.year = carDto.getYear();
        this.price = carDto.getPrice();
        this.color = carDto.getColor();
        this.fuelType = carDto.getFuelType();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
