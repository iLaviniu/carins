package com.example.carins.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.carins.web.InsuranceClaim;

public interface ClaimRepository extends JpaRepository<InsuranceClaim, Long> {}