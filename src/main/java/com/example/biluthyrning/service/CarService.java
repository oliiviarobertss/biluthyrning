package com.example.biluthyrning.service;

import com.example.biluthyrning.model.Car;
import com.example.biluthyrning.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CarService {

    private final CarRepository carRepository;

    public CarService(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    public void addCar(Car car) {
        carRepository.save(car);
    }

    public List<Car> searchCars(String query) {
        return carRepository.findAll().stream()
                .filter(car -> car.getBrand().toLowerCase().contains(query.toLowerCase()) ||
                        car.getModel().toLowerCase().contains(query.toLowerCase()) ||
                        car.getRegistrationNumber().toLowerCase().contains(query.toLowerCase()))
                .collect(Collectors.toList());
    }
}
