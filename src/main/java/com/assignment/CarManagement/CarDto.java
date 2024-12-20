package com.assignment.CarManagement;

public class CarDto {
    private String name;
    private String model;
    private Double price;
    private String color;
    private int year;
    private String fuelType;

    public CarDto() {
    }

    public CarDto(String name, String model, Double price, String color, int year, String fuelType) {
        this.name = name;
        this.model = model;
        this.price = price;
        this.color = color;
        this.year = year;
        this.fuelType = fuelType;
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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }
}
