package com.artificial.smart_insurance_claim.Model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "insurance_claims")
public class InsuranceClaimEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String claimNumber;

    private String customerName;

    private String policyNumber;

    private String claimType;

    private String incidentDescription;

    private Double claimAmount;

    private Double approvedAmount;

    private String claimStatus;

    private LocalDateTime createdAt = LocalDateTime.now();
}