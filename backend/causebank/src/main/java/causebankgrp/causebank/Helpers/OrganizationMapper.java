package causebankgrp.causebank.Helpers;

import causebankgrp.causebank.Entity.Organization;

import causebankgrp.causebank.Dto.OrganizationDTO.Request.OrganizationRequest;
import causebankgrp.causebank.Dto.OrganizationDTO.Response.OrganizationResponse;
import causebankgrp.causebank.Entity.User;

public class OrganizationMapper {

    public static Organization toEntity(OrganizationRequest requestDTO, User user) {
        if (requestDTO == null) {
            return null;
        }

        Organization organization = new Organization();
        organization.setUser(user);
        organization.setName(requestDTO.getName());
        organization.setDescription(requestDTO.getDescription());
        organization.setWebsiteUrl(requestDTO.getWebsiteUrl());
        organization.setLogoUrl(requestDTO.getLogoUrl());
        organization.setRegistrationNumber(requestDTO.getRegistrationNumber());
        organization.setTaxId(requestDTO.getTaxId());
        organization.setAddressLine1(requestDTO.getAddressLine1());
        organization.setAddressLine2(requestDTO.getAddressLine2());
        organization.setCity(requestDTO.getCity());
        organization.setState(requestDTO.getState());
        organization.setPostalCode(requestDTO.getPostalCode());
        organization.setCountry(requestDTO.getCountry());

        return organization;
    }

    public static OrganizationResponse toResponseDTO(Organization organization) {
        if (organization == null) {
            return null;
        }

        OrganizationResponse responseDTO = new OrganizationResponse();
        responseDTO.setId(organization.getId());
        responseDTO.setName(organization.getName());
        responseDTO.setDescription(organization.getDescription());
        responseDTO.setWebsiteUrl(organization.getWebsiteUrl());
        responseDTO.setLogoUrl(organization.getLogoUrl());
        responseDTO.setRegistrationNumber(organization.getRegistrationNumber());
        responseDTO.setTaxId(organization.getTaxId());
        responseDTO.setAddressLine1(organization.getAddressLine1());
        responseDTO.setAddressLine2(organization.getAddressLine2());
        responseDTO.setCity(organization.getCity());
        responseDTO.setState(organization.getState());
        responseDTO.setPostalCode(organization.getPostalCode());
        responseDTO.setCountry(organization.getCountry());
        responseDTO.setIsVerified(organization.getIsVerified());
        responseDTO.setCreatedAt(organization.getCreatedAt());
        responseDTO.setUpdatedAt(organization.getUpdatedAt());
        
        // Check if user is not null before accessing its ID
        if (organization.getUser() != null) {
            responseDTO.setUserId(organization.getUser().getId());
        }

        return responseDTO;
    }

    // Method to update an existing organization from a request DTO
    public static Organization updateEntity(Organization existingOrganization, OrganizationRequest requestDTO) {
        if (requestDTO == null || existingOrganization == null) {
            return null;
        }

        existingOrganization.setName(requestDTO.getName());
        existingOrganization.setDescription(requestDTO.getDescription());
        existingOrganization.setWebsiteUrl(requestDTO.getWebsiteUrl());
        existingOrganization.setLogoUrl(requestDTO.getLogoUrl());
        existingOrganization.setRegistrationNumber(requestDTO.getRegistrationNumber());
        existingOrganization.setTaxId(requestDTO.getTaxId());
        existingOrganization.setAddressLine1(requestDTO.getAddressLine1());
        existingOrganization.setAddressLine2(requestDTO.getAddressLine2());
        existingOrganization.setCity(requestDTO.getCity());
        existingOrganization.setState(requestDTO.getState());
        existingOrganization.setPostalCode(requestDTO.getPostalCode());
        existingOrganization.setCountry(requestDTO.getCountry());

        return existingOrganization;
    }
}