package com.example.carins.repo;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.carins.model.Car;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    // TODO: enforce unique VIN at DB and via validation (exercise)
    @EntityGraph(attributePaths = {"owner"})
    List<Car> findAll();
    Car findByVin(String vin);
}