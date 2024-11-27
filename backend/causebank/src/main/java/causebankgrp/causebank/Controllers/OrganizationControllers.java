package causebankgrp.causebank.Controllers;

import causebankgrp.causebank.Dto.OrganizationDTO.Request.OrganizationRequest;
import causebankgrp.causebank.Dto.OrganizationDTO.Response.ApiResponse;
import causebankgrp.causebank.Dto.OrganizationDTO.Response.OrganizationResponse;
import causebankgrp.causebank.Services.OrganizationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/organization")
@RequiredArgsConstructor
public class OrganizationControllers {

    private final OrganizationService organizationService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<OrganizationResponse>> createOrganization(
            @RequestBody OrganizationRequest request) {
        ApiResponse<OrganizationResponse> response = organizationService.createOrganization(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<OrganizationResponse>> updateOrganization(
            @PathVariable UUID id,
            @RequestBody OrganizationRequest request) {
        ApiResponse<OrganizationResponse> response = organizationService.updateOrganization(id, request);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteOrganization(
            @PathVariable UUID id) {
        ApiResponse<Void> response = organizationService.deleteOrganization(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<OrganizationResponse>> getOrganization(
            @PathVariable UUID id) {
        ApiResponse<OrganizationResponse> response = organizationService.getOrganization(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/allOrganizations")
    public ResponseEntity<ApiResponse<List<OrganizationResponse>>> getAllOrganizations() {
        ApiResponse<List<OrganizationResponse>> response = organizationService.getAllOrganizations();
        return ResponseEntity.ok(response);
    }
}