package causebankgrp.causebank.Repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import causebankgrp.causebank.Entity.Organization;

@Repository
public interface  OrganizationRepository extends JpaRepository<Organization, UUID>  {
      // Note: Changed from findByEmail to findByName as Organization doesn't have an email field
      Optional<Organization> findByName(String name);
    
      // Check if an organization with a specific name already exists
      boolean existsByName(String name);
    
}
