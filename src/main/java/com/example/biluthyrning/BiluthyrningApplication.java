package com.example.biluthyrning;

import com.example.biluthyrning.model.Car;
import com.example.biluthyrning.model.Customer;
import com.example.biluthyrning.repository.CarRepository;
import com.example.biluthyrning.repository.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BiluthyrningApplication {

	public static void main(String[] args) {
		SpringApplication.run(BiluthyrningApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(CarRepository carRepository, CustomerRepository customerRepository) {
		return (args) -> {
			// Add example data
			carRepository.save(new Car("ABC123", "Volvo", "XC60", true, 1200));
			carRepository.save(new Car("DEF456", "BMW", "320i", true, 1500));

			customerRepository.save(new Customer("Anna Svensson", "anna.svensson@mail.com", "0701234567"));
			customerRepository.save(new Customer("Erik Larsson", "erik.larsson@mail.com", "0707654321"));
		};
	}
}
