package com.example.carins.web;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.carins.model.Car;
import com.example.carins.repo.ClaimRepository;
import com.example.carins.service.CarService;
import com.example.carins.web.dto.CarDto;

@RestController
@RequestMapping("/api")
public class CarController {

    private final CarService service;
    private final ClaimRepository claimRepository;

    public CarController(CarService service, ClaimRepository claimRepository) {
        this.service = service;
        this.claimRepository = claimRepository;
    }

    @GetMapping("/cars")
    public List<CarDto> getCars() {
        return service.listCars().stream().map(this::toDto).toList();
    }

    @GetMapping("/cars/{carId}/insurance-valid")
    public ResponseEntity<?> isInsuranceValid(@PathVariable Long carId, @RequestParam String date) {
        // TODO: validate date format and handle errors consistently

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate d = LocalDate.parse(date, formatter);
            boolean valid = service.isInsuranceValid(carId, d);
            return ResponseEntity.ok(new InsuranceValidityResponse(carId, d.toString(), valid));
        } catch (DateTimeParseException e) {
            return ResponseEntity.badRequest().body("Invalid date format. Expected yyyy-MM-dd.");
        }

    }

    private CarDto toDto(Car c) {
        var o = c.getOwner();
        return new CarDto(c.getId(), c.getVin(), c.getMake(), c.getModel(), c.getYearOfManufacture(),
                o != null ? o.getId() : null,
                o != null ? o.getName() : null,
                o != null ? o.getEmail() : null);
    }

    public record InsuranceValidityResponse(Long carId, String date, boolean valid) {}



    @PostMapping("/cars/{carId}/claims")
    public ResponseEntity<?> registerClaim(@PathVariable String vin, @Validated @RequestBody ClaimRequest request, UriComponentsBuilder uriBuilder) {

        Car car = service.findByVin(vin);

        InsuranceClaim claim = new InsuranceClaim();
        claim.setCar(car);
        claim.setClaimDate(request.getClaimDate());
        claim.setDescription(request.getDescription());
        claim.setAmount(request.getAmount());

        InsuranceClaim saved = claimRepository.save(claim);

        return ResponseEntity.created(uriBuilder.path("/api/cars/{carId}/claims/{id}").buildAndExpand(saved.getId(), saved.getId()).toUri()).body(saved);
    }
}
