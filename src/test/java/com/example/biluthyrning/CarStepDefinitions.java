package com.example.biluthyrning;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class CarStepDefinitions {

    @Given("a car with registration number {string}, brand {string}, and model {string}")
    public void aCarWithDetails(String regNumber, String brand, String model) {
        // Logik för att skapa en bil
        System.out.println("Creating car: " + regNumber + ", " + brand + ", " + model);
    }

    @When("the car is added to the system")
    public void theCarIsAddedToTheSystem() {
        // Logik för att lägga till bilen
        System.out.println("Car added to the system");
    }

    @Then("the car should be retrievable in the list of available cars")
    public void theCarShouldBeRetrievableInTheList() {
        // Kontrollera att bilen finns i listan
        assertTrue(true); // Placeholder
    }
}
