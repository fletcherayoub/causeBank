package causebankgrp.causebank.Dto.OrganizationDTO.Response;

import java.time.ZonedDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrganizationResponse {
    private UUID id;
    private String name;
    private String description;
    private String websiteUrl;
    private String logoUrl;
    private String registrationNumber;
    private String taxId;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private Boolean isVerified;
    private ZonedDateTime createdAt;
    private ZonedDateTime updatedAt;
    private UUID userId; // ID of the associated user
}
