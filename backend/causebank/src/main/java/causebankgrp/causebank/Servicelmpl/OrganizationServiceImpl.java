package causebankgrp.causebank.Servicelmpl;

import causebankgrp.causebank.Dto.OrganizationDTO.Request.OrganizationRequest;
import causebankgrp.causebank.Dto.OrganizationDTO.Response.ApiResponse;
import causebankgrp.causebank.Dto.OrganizationDTO.Response.OrganizationResponse;
import causebankgrp.causebank.Entity.Organization;
import causebankgrp.causebank.Entity.User;
import causebankgrp.causebank.Exception.Auth_Authorize.ForbiddenException;
import causebankgrp.causebank.Exception.Auth_Authorize.ResourceNotFoundException;
import causebankgrp.causebank.Helpers.OrganizationMapper;
import causebankgrp.causebank.Repository.OrganizationRepository;
import causebankgrp.causebank.Services.OrganizationService;
import causebankgrp.causebank.Utils.Auth_Authorize.AuthenticationUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {
    private final OrganizationRepository organizationRepository;
    private final AuthenticationUtils authenticationUtils;

    @Override
    @Transactional
    public ApiResponse<OrganizationResponse> createOrganization(OrganizationRequest request) {
        User authUser = authenticationUtils.getCurrentAuthenticatedUser();
        
        // Check if organization name is already taken
        if (organizationRepository.existsByName(request.getName())) {
            return ApiResponse.error("Organization name is already in use");
        }
        
        Organization organization = OrganizationMapper.toEntity(request, authUser);
        Organization savedOrganization = organizationRepository.save(organization);
        
        return ApiResponse.success(
            OrganizationMapper.toResponseDTO(savedOrganization), 
            "Organization created successfully"
        );
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<OrganizationResponse> getOrganization(UUID id) {
        Organization organization = organizationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Organization not found"));
        
        // User authUser = authenticationUtils.getCurrentAuthenticatedUser();
        // if (!organization.getUser().getId().equals(authUser.getId()) && 
        //     !authenticationUtils.isCurrentUserAdmin()) {
        //     return ApiResponse.error("You are not authorized to view this organization");
        // }
        
        return ApiResponse.success(
            OrganizationMapper.toResponseDTO(organization), 
            "Organization retrieved successfully"
        );
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<List<OrganizationResponse>> getUserOrganizations() {
        User authUser = authenticationUtils.getCurrentAuthenticatedUser();
        
        List<Organization> organizations = organizationRepository.findByUser_Id(authUser.getId());
        
        return ApiResponse.success(
            organizations.stream()
                .map(OrganizationMapper::toResponseDTO)
                .collect(Collectors.toList()), 
            "User organizations retrieved successfully"
        );
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<List<OrganizationResponse>> getAllOrganizations() {
        // Only admin can access all organizations
        // if (!authenticationUtils.isCurrentUserAdmin()) {
        //     return ApiResponse.error("Only admins can retrieve all organizations");
        // }
        
        List<Organization> organizations = organizationRepository.findAll();
        
        return ApiResponse.success(
            organizations.stream()
                .map(OrganizationMapper::toResponseDTO)
                .collect(Collectors.toList()), 
            "All organizations retrieved successfully"
        );
    }

    @Override
    @Transactional
    public ApiResponse<OrganizationResponse> updateOrganization(UUID id, OrganizationRequest request) {
        Organization existingOrganization = organizationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Organization not found"));
        
        User authUser = authenticationUtils.getCurrentAuthenticatedUser();
        if (!existingOrganization.getUser().getId().equals(authUser.getId()) && 
            !authenticationUtils.isCurrentUserAdmin()) {
            return ApiResponse.error("You are not authorized to update this organization");
        }
        
        // Check if new name (if changed) is already taken
        if (request.getName() != null && 
            !request.getName().equals(existingOrganization.getName()) && 
            organizationRepository.existsByName(request.getName())) {
            return ApiResponse.error("Organization name is already in use");
        }
        
        Organization updatedOrganization = OrganizationMapper.updateEntity(existingOrganization, request);
        Organization savedOrganization = organizationRepository.save(updatedOrganization);
        
        return ApiResponse.success(
            OrganizationMapper.toResponseDTO(savedOrganization), 
            "Organization updated successfully"
        );
    }

    @Override
    @Transactional
    public ApiResponse<Void> deleteOrganization(UUID id) {
        Organization existingOrganization = organizationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Organization not found"));
        
        User authUser = authenticationUtils.getCurrentAuthenticatedUser();
        if (!existingOrganization.getUser().getId().equals(authUser.getId()) && 
            !authenticationUtils.isCurrentUserAdmin()) {
            return ApiResponse.error("You are not authorized to delete this organization");
        }
        
        organizationRepository.delete(existingOrganization);
        
        return ApiResponse.success(null, "Organization deleted successfully");
    }

    @Override
    public boolean isOrganizationNameTaken(String name) {
        return organizationRepository.existsByName(name);
    }

    @Override
    public boolean isRegistrationNumberTaken(String registrationNumber) {
        return organizationRepository.existsByRegistrationNumber(registrationNumber);
    }

    @Override
    @Transactional
    public ApiResponse<OrganizationResponse> verifyOrganization(UUID id) {
        // Only admin can verify organizations
        if (!authenticationUtils.isCurrentUserAdmin()) {
            return ApiResponse.error("Only admins can verify organizations");
        }
        
        Organization organization = organizationRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Organization not found"));
        
        organization.setIsVerified(true);
        Organization savedOrganization = organizationRepository.save(organization);
        
        return ApiResponse.success(
            OrganizationMapper.toResponseDTO(savedOrganization), 
            "Organization verified successfully"
        );
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<List<OrganizationResponse>> getOrganizationsByCity(String city) {
        List<Organization> organizations = organizationRepository.findByCity(city);
        
        return ApiResponse.success(
            organizations.stream()
                .map(OrganizationMapper::toResponseDTO)
                .collect(Collectors.toList()), 
            "Organizations retrieved by city successfully"
        );
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<List<OrganizationResponse>> getOrganizationsByCountry(String country) {
        List<Organization> organizations = organizationRepository.findByCountry(country);
        
        return ApiResponse.success(
            organizations.stream()
                .map(OrganizationMapper::toResponseDTO)
                .collect(Collectors.toList()), 
            "Organizations retrieved by country successfully"
        );
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<List<OrganizationResponse>> getOrganizationsByVerificationStatus(Boolean isVerified) {
        // Only admin can retrieve organizations by verification status
        if (!authenticationUtils.isCurrentUserAdmin()) {
            return ApiResponse.error("Only admins can retrieve organizations by verification status");
        }
        
        List<Organization> organizations = organizationRepository.findByIsVerified(isVerified);
        
        return ApiResponse.success(
            organizations.stream()
                .map(OrganizationMapper::toResponseDTO)
                .collect(Collectors.toList()), 
            "Organizations retrieved by verification status successfully"
        );
    }
}