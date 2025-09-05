package com.example.carins;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.carins.service.CarService;
import com.example.carins.web.ClaimRequest;
import com.example.carins.web.InsuranceClaim;

@SpringBootTest(classes = CarInsuranceApplication.class)
class CarInsuranceApplicationTests {

    @Autowired
    CarService service;

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void insuranceValidityBasic() {
        assertTrue(service.isInsuranceValid(1L, LocalDate.parse("2024-06-01")));
        assertTrue(service.isInsuranceValid(1L, LocalDate.parse("2025-06-01")));
        assertFalse(service.isInsuranceValid(2L, LocalDate.parse("2025-02-01")));
    }

    @Test
    void insuranceClaimTest() {
        ClaimRequest request = new ClaimRequest();
        request.setClaimDate(LocalDate.of(2025, 6, 1));
        request.setDescription("Accident on highway, front bumper damage");
        request.setAmount(1200.50);

        ResponseEntity<InsuranceClaim> response = restTemplate.postForEntity(
                "http://localhost:8080/api/cars/1/claims",
                request,
                InsuranceClaim.class);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody().getId());
    }
}
