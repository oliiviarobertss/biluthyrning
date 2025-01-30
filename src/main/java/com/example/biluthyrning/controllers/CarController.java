package com.example.biluthyrning.controllers;

import com.example.biluthyrning.model.Car;
import com.example.biluthyrning.repository.CarRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cars")
public class CarController {

    private final CarRepository carRepository;

    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    // Visa alla bilar
    @GetMapping
    public String getAllCars(Model model) {
        List<Car> cars = carRepository.findAll();
        model.addAttribute("cars", cars);
        return "car-list"; // Thymeleaf-sida
    }

    // Lägg till bil
    @PostMapping("/add-car")
    public String addCar(@RequestParam String brand,
                         @RequestParam String model,
                         @RequestParam String registrationNumber,
                         @RequestParam int rentalPrice) {
        Car car = new Car(brand, model, registrationNumber, true, rentalPrice);
        carRepository.save(car);
        return "redirect:/cars";
    }

    // Ta bort bil
    @PostMapping("/delete-car")
    public String deleteCar(@RequestParam Long id) {
        carRepository.deleteById(id);
        return "redirect:/cars";
    }
}