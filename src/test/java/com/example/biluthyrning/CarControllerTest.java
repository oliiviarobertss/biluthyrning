package com.example.biluthyrning;

import com.example.biluthyrning.controllers.CarController;
import com.example.biluthyrning.model.Car;
import com.example.biluthyrning.repository.CarRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

class CarControllerTest {

    private MockMvc mockMvc;

    @Mock
    private CarRepository carRepository;

    @InjectMocks
    private CarController carController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(carController).build();
    }

    @Test
    void shouldReturnAllCars() throws Exception {
        List<Car> cars = Arrays.asList(
                new Car("Volvo", "XC60", "ABC123", true, 1200),
                new Car("BMW", "320i", "DEF456", true, 1500)
        );

        when(carRepository.findAll()).thenReturn(cars);

        mockMvc.perform(get("/cars"))
                .andExpect(status().isOk())
                .andExpect(view().name("car-list"))
                .andExpect(model().attributeExists("cars"));
    }

    @Test
    void shouldAddNewCar() throws Exception {
        mockMvc.perform(post("/cars/add-car")
                        .param("brand", "Toyota")
                        .param("model", "Corolla")
                        .param("registrationNumber", "GHI789")
                        .param("rentalPrice", "1000"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cars"));

        verify(carRepository, times(1)).save(any(Car.class));
    }

    @Test
    void shouldDeleteCar() throws Exception {
        Long carId = 1L;
        when(carRepository.existsById(carId)).thenReturn(true);

        mockMvc.perform(post("/cars/delete-car")
                        .param("id", carId.toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/cars"));

        verify(carRepository, times(1)).deleteById(carId);
    }
}
