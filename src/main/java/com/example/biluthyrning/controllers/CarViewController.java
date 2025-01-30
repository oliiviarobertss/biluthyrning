package com.example.biluthyrning.controller;

import com.example.biluthyrning.model.Car;
import com.example.biluthyrning.service.CarService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CarViewController {

    private final CarService carService;

    public CarViewController(CarService carService) {
        this.carService = carService;
    }

    @GetMapping("/car-list")
    public String showCarList(Model model, @RequestParam(required = false) String query) {
        List<Car> cars;
        if (query != null && !query.isEmpty()) {
            cars = carService.searchCars(query);
        } else {
            cars = carService.getAllCars();
        }
        model.addAttribute("cars", cars);
        return "car-list";
    }

    @PostMapping("/add-car")
    public String addCar(@RequestParam String brand, @RequestParam String model,
                         @RequestParam String registrationNumber, @RequestParam int rentalPrice) {
        Car car = new Car();
        car.setBrand(brand);
        car.setModel(model);
        car.setRegistrationNumber(registrationNumber);
        car.setRentalPrice(rentalPrice);
        car.setAvailable(true);
        carService.addCar(car);
        return "redirect:/car-list";
    }
}
