package com.example.carins.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.carins.model.Car;
import com.example.carins.repo.CarRepository;
import com.example.carins.repo.InsurancePolicyRepository;

@Service
public class CarService {

    private final CarRepository carRepository;
    private final InsurancePolicyRepository policyRepository;

    public CarService(CarRepository carRepository, InsurancePolicyRepository policyRepository) {
        this.carRepository = carRepository;
        this.policyRepository = policyRepository;
    }

    public List<Car> listCars() {
        return carRepository.findAll();
    }

    public boolean isInsuranceValid(Long carId, LocalDate date) {
        if (carId == null || date == null) return false;
        
        // TODO: optionally throw NotFound if car does not exist

        if(policyRepository.existsActiveOnDate(carId, date)) {
            return true;
        } else {
            return false;
        }
    }

    public Object findById(Long carId) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Car findByVin(String vin) {
        return carRepository.findByVin(vin);
    }
}
