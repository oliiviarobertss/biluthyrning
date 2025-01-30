package com.example.biluthyrning;

import com.example.biluthyrning.model.Customer;
import com.example.biluthyrning.repository.CustomerRepository;
import com.example.biluthyrning.controllers.CustomerController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class CustomerControllerTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private Model model;

    @InjectMocks
    private CustomerController customerController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void shouldReturnAllCustomers() {
        // Skapa testkunder
        List<Customer> customers = Arrays.asList(
                new Customer("Anna Svensson", "anna.svensson@mail.com", "0701234567"),
                new Customer("Erik Larsson", "erik.larsson@mail.com", "0707654321")
        );

        // Mocka repository
        when(customerRepository.findAll()).thenReturn(customers);

        // Kör metoden
        String viewName = customerController.getAllCustomers(model);

        // Verifiera att rätt vy returneras och att kunder läggs till i modellen
        verify(model).addAttribute("customers", customers);
        assertEquals("customer-list", viewName);
    }

    @Test
    void shouldAddNewCustomer() {
        Customer newCustomer = new Customer("Lisa Andersson", "lisa.andersson@mail.com", "0701234567");

        // Kör metoden
        String viewName = customerController.addCustomer(newCustomer);

        // Verifiera att kunden sparas och att vi omdirigeras rätt
        verify(customerRepository).save(newCustomer);
        assertEquals("redirect:/customers", viewName);
    }

    @Test
    void shouldDeleteCustomer() {
        Long customerId = 1L;

        // Mocka att kunden finns i databasen
        when(customerRepository.existsById(customerId)).thenReturn(true);

        // Kör deleteCustomer-metoden
        customerController.deleteCustomer(customerId);

        // Verifiera att existById och deleteById anropas
        verify(customerRepository).existsById(customerId);
        verify(customerRepository).deleteById(customerId);
    }

}
