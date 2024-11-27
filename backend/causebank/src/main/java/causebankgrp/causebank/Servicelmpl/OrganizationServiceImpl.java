package causebankgrp.causebank.Servicelmpl;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import causebankgrp.causebank.Dto.OrganizationDTO.Request.OrganizationRequest;
import causebankgrp.causebank.Dto.OrganizationDTO.Response.ApiResponse;
import causebankgrp.causebank.Dto.OrganizationDTO.Response.OrganizationResponse;
import causebankgrp.causebank.Entity.Organization;
import causebankgrp.causebank.Entity.User;
import causebankgrp.causebank.Helpers.OrganizationMapper;
import causebankgrp.causebank.Repository.OrganizationRepository;
import causebankgrp.causebank.Repository.UserRepository;
import causebankgrp.causebank.Services.OrganizationService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public ApiResponse<OrganizationResponse> createOrganization(OrganizationRequest request) {
        // Check if user exists
        User user = userRepository.findById(request.getUserId())
            .orElseThrow(() -> new RuntimeException("User not found"));

        // Check if organization with same name already exists
        if (organizationRepository.existsByName(request.getName())) {
            throw new RuntimeException("Organization with this name already exists");
        }

        // Create new organization using mapper
        Organization organization = OrganizationMapper.toEntity(request, user);

        // Save organization
        Organization savedOrganization = organizationRepository.save(organization);

        // Convert to response DTO
        OrganizationResponse responseDTO = OrganizationMapper.toResponseDTO(savedOrganization);

        return ApiResponse.success(responseDTO, "Organization created successfully");
    }

    @Override
    @Transactional
    public ApiResponse<OrganizationResponse> updateOrganization(UUID id, OrganizationRequest request) {
        // Find existing organization
        Organization existingOrganization = organizationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Organization not found"));

        // Update organization details using mapper
        Organization updatedOrganization = OrganizationMapper.updateEntity(existingOrganization, request);

        // Save updated organization
        Organization savedOrganization = organizationRepository.save(updatedOrganization);

        // Convert to response DTO
        OrganizationResponse responseDTO = OrganizationMapper.toResponseDTO(savedOrganization);

        return ApiResponse.success(responseDTO, "Organization updated successfully");
    }

    @Override
    @Transactional
    public ApiResponse<Void> deleteOrganization(UUID id) {
        // Check if organization exists
        Organization organization = organizationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Organization not found"));

        // Delete organization
        organizationRepository.delete(organization);

        return ApiResponse.success(null, "Organization deleted successfully");
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<OrganizationResponse> getOrganization(UUID id) {
        // Find organization
        Organization organization = organizationRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Organization not found"));

        // Convert to response DTO
        OrganizationResponse responseDTO = OrganizationMapper.toResponseDTO(organization);

        return ApiResponse.success(responseDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public ApiResponse<List<OrganizationResponse>> getAllOrganizations() {
        // Fetch all organizations
        List<Organization> organizations = organizationRepository.findAll();

        // Convert to response DTOs
        List<OrganizationResponse> responseDTOs = organizations.stream()
            .map(OrganizationMapper::toResponseDTO)
            .collect(Collectors.toList());

        return ApiResponse.success(responseDTOs);
    }
}