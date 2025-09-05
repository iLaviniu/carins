/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.example.carins.web;


import java.time.LocalDate;

import jakarta.validation.constraints.NotNull;

public class ClaimRequest {

    @NotNull(message = "Claim date is required")
    private LocalDate claimDate;

    @NotNull(message = "Description is required")
    private String description;

    @NotNull(message = "Amount is required")
    private Double amount;

    // getters and setters
    public LocalDate getClaimDate() { return claimDate; }
    public void setClaimDate(LocalDate claimDate) { this.claimDate = claimDate; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }
}