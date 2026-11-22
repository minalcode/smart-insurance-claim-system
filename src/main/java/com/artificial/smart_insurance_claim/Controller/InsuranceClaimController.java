package com.artificial.smart_insurance_claim.Controller;

import com.artificial.smart_insurance_claim.Model.InsuranceClaimEntity;
import com.artificial.smart_insurance_claim.Service.InsuranceClaimService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/claims")
public class InsuranceClaimController {

    private final InsuranceClaimService service;

    public InsuranceClaimController(InsuranceClaimService service) {
        this.service = service;
    }

    // Submit Claim
    @PostMapping
    public InsuranceClaimEntity submitClaim(
            @RequestBody InsuranceClaimEntity claim) {

        return service.submitClaim(claim);
    }

    // Get All Claims
    @GetMapping
    public List<InsuranceClaimEntity> getAllClaims() {
        return service.getAllClaims();
    }

    // Get Claim By ID
    @GetMapping("/{id}")
    public InsuranceClaimEntity getClaimById(
            @PathVariable Long id) {

        return service.getClaimById(id);
    }

    // Search By Claim Number
    @GetMapping("/track/{claimNumber}")
    public InsuranceClaimEntity getClaimByNumber(
            @PathVariable String claimNumber) {

        return service.getClaimByNumber(claimNumber);
    }

    // Update Claim Status
    @PutMapping("/{id}/status")
    public InsuranceClaimEntity updateClaimStatus(
            @PathVariable Long id,
            @RequestParam String status,
            @RequestParam(required = false) Double approvedAmount) {

        return service.updateClaimStatus(id, status, approvedAmount);
    }

    // Delete Claim
    @DeleteMapping("/{id}")
    public String deleteClaim(@PathVariable Long id) {

        service.deleteClaim(id);

        return "Insurance claim deleted successfully";
    }
}