package com.artificial.smart_insurance_claim.Repository;

import com.artificial.smart_insurance_claim.Model.InsuranceClaimEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InsuranceClaimRepository
        extends JpaRepository<InsuranceClaimEntity, Long> {

    Optional<InsuranceClaimEntity> findByClaimNumber(String claimNumber);
}