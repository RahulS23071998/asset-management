package com.assetcircle.assetmanagement.organization.repository;

import com.assetcircle.assetmanagement.organization.model.OrganizationModel;
import com.assetcircle.assetmanagement.enums.OrganizationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrganizationRepository extends JpaRepository<OrganizationModel,Long> {

    Optional<OrganizationModel> findByOrganizationEmailId(String email);
    boolean existsByContactNumber(String mobileNum);
    Optional<OrganizationModel> findByOrganizationCode(String organizationCode);

    List<OrganizationModel> findAllByStatus(OrganizationStatus organizationStatus);
}

