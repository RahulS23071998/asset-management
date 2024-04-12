package com.assetcircle.assetmanagement.organization.service;

import com.assetcircle.assetmanagement.organization.dto.OrganizationDto;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface OrganizationService {

    void registerOrganization(OrganizationDto organizationDto);

    Optional<OrganizationDto> getOrganizationByCode(String organizationCode);

    Map<String,List<OrganizationDto>> getAllOrganization(String status);

   List<OrganizationDto> getAllOrganization();

    boolean  updateOrganization(Long id,OrganizationDto organizationDto);

    boolean disableOrganization(String organizationCode);

}
