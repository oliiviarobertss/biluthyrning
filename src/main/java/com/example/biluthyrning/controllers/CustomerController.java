package com.example.biluthyrning.controllers;

import com.example.biluthyrning.model.Customer;
import com.example.biluthyrning.repository.CustomerRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customers")
public class CustomerController {

    private final CustomerRepository customerRepository;

    public CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Hämta alla kunder och visa dem i en Thymeleaf-vy
    @GetMapping
    public String getAllCustomers(Model model) {
        List<Customer> customers = customerRepository.findAll();
        model.addAttribute("customers", customers);
        model.addAttribute("customer", new Customer()); // För formuläret
        return "customer-list";
    }

    // Lägga till en ny kund
    @PostMapping("/add")
    public String addCustomer(@ModelAttribute Customer customer) {
        customerRepository.save(customer);
        return "redirect:/customers";
    }

    // Ta bort en kund
    @PostMapping("/delete")
    public String deleteCustomer(@RequestParam Long id) {
        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
        } else {
            System.out.println("Kunden med id " + id + " hittades inte.");
        }
        return "redirect:/customers";
    }

    // Söka efter kunder baserat på namn
    @GetMapping("/search")
    public String searchCustomers(@RequestParam String query, Model model) {
        List<Customer> customers = customerRepository.findByNameContainingIgnoreCase(query);
        model.addAttribute("customers", customers);
        return "customer-list";
    }
}
