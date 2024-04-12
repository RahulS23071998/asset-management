package com.assetcircle.assetmanagement.organization.mapper;

import com.assetcircle.assetmanagement.organization.dto.OrganizationDto;
import com.assetcircle.assetmanagement.organization.model.OrganizationModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OrganizationMapper {

    OrganizationMapper INSTANCE = Mappers.getMapper(OrganizationMapper.class);

    OrganizationDto   toOrganizationDto(OrganizationModel organizationModel);
    OrganizationModel toOrganization(OrganizationDto organizationDto);

}
