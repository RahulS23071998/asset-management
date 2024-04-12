package com.assetmanagement.desklite.organization.mapper;

import com.assetmanagement.desklite.organization.dto.OrganizationDto;
import com.assetmanagement.desklite.organization.model.OrganizationModel;

public interface OrganizationMapper {

    OrganizationDto toOrganizationDto(OrganizationModel organizationModel);
    OrganizationModel toOrganization(OrganizationDto organizationDto);

}
