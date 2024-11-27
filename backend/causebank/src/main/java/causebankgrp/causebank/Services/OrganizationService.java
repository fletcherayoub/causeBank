package causebankgrp.causebank.Services;

import java.util.List;
import java.util.UUID;

import causebankgrp.causebank.Dto.OrganizationDTO.Request.OrganizationRequest;
import causebankgrp.causebank.Dto.OrganizationDTO.Response.ApiResponse;
import causebankgrp.causebank.Dto.OrganizationDTO.Response.OrganizationResponse;

public interface OrganizationService {
    ApiResponse<OrganizationResponse> createOrganization(OrganizationRequest request);
    ApiResponse<OrganizationResponse> updateOrganization(UUID id, OrganizationRequest request);
    ApiResponse<Void> deleteOrganization(UUID id);
    ApiResponse<OrganizationResponse> getOrganization(UUID id);
    ApiResponse<List<OrganizationResponse>> getAllOrganizations();
}
