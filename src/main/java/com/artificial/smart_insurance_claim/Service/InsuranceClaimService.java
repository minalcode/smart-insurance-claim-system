package com.artificial.smart_insurance_claim.Service;

import com.artificial.smart_insurance_claim.Model.InsuranceClaimEntity;
import com.artificial.smart_insurance_claim.Repository.InsuranceClaimRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class InsuranceClaimService {

    private final InsuranceClaimRepository repository;

    public InsuranceClaimService(InsuranceClaimRepository repository) {
        this.repository = repository;
    }

    // Submit Claim
    public InsuranceClaimEntity submitClaim(InsuranceClaimEntity claim) {

        claim.setClaimNumber(
                "CLM-" + UUID.randomUUID()
                        .toString().substring(0, 8).toUpperCase()
        );

        claim.setClaimStatus("SUBMITTED");

        claim.setApprovedAmount(0.0);

        claim.setCreatedAt(java.time.LocalDateTime.now());

        return repository.save(claim);
    }

    // Get All Claims
    public List<InsuranceClaimEntity> getAllClaims() {
        return repository.findAll();
    }

    // Get Claim By ID
    public InsuranceClaimEntity getClaimById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Claim not found"));
    }

    // Search Claim By Claim Number
    public InsuranceClaimEntity getClaimByNumber(String claimNumber) {

        return repository.findByClaimNumber(claimNumber)
                .orElseThrow(() ->
                        new RuntimeException("Claim number not found"));
    }

    // Update Claim Status
    public InsuranceClaimEntity updateClaimStatus(
            Long id, String status, Double approvedAmount) {

        InsuranceClaimEntity claim = getClaimById(id);

        String newStatus = status.toUpperCase();

        if (newStatus.equals("APPROVED")) {

            if (approvedAmount == null ||
                    approvedAmount <= 0 ||
                    approvedAmount > claim.getClaimAmount()) {

                throw new RuntimeException(
                        "Approved amount must be greater than 0 and cannot exceed claim amount"
                );
            }

            claim.setApprovedAmount(approvedAmount);
        }

        if (newStatus.equals("REJECTED")) {
            claim.setApprovedAmount(0.0);
        }

        claim.setClaimStatus(newStatus);

        return repository.save(claim);
    }

    // Delete Claim
    public void deleteClaim(Long id) {

        InsuranceClaimEntity claim = getClaimById(id);

        repository.delete(claim);
    }
}