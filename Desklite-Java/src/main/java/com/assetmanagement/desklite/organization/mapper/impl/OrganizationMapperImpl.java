package com.assetmanagement.desklite.organization.mapper.impl;

import com.assetmanagement.desklite.organization.dto.OrganizationDto;
import com.assetmanagement.desklite.organization.mapper.OrganizationMapper;
import com.assetmanagement.desklite.organization.model.OrganizationModel;
import com.assetmanagement.desklite.organization.model.OrganizationStatus;
import org.springframework.stereotype.Service;

@Service
public class OrganizationMapperImpl implements OrganizationMapper {
    public OrganizationMapperImpl() {
    }

    public OrganizationDto toOrganizationDto(OrganizationModel organizationModel) {
        if (organizationModel == null) {
            return null;
        } else {
            OrganizationDto.OrganizationDtoBuilder organizationDto = OrganizationDto.builder();
            organizationDto.organizationName(organizationModel.getOrganizationName());
            organizationDto.organizationEmailId(organizationModel.getOrganizationEmailId());
            organizationDto.contactNumber(organizationModel.getContactNumber());
            organizationDto.location(organizationModel.getLocation());
            organizationDto.organizationCode(organizationModel.getOrganizationCode());
            if (organizationModel.getStatus() != null) {
                organizationDto.status(organizationModel.getStatus().name());
            }

            return organizationDto.build();
        }
    }

    public OrganizationModel toOrganization(OrganizationDto organizationDto) {
        if (organizationDto == null) {
            return null;
        } else {
            OrganizationModel.OrganizationModelBuilder organizationModel = OrganizationModel.builder();
            organizationModel.organizationName(organizationDto.getOrganizationName());
            organizationModel.organizationEmailId(organizationDto.getOrganizationEmailId());
            organizationModel.contactNumber(organizationDto.getContactNumber());
            organizationModel.location(organizationDto.getLocation());
            organizationModel.organizationCode(organizationDto.getOrganizationCode());
            if (organizationDto.getStatus() != null) {
                organizationModel.status((OrganizationStatus)Enum.valueOf(OrganizationStatus.class, organizationDto.getStatus()));
            }

            return organizationModel.build();
        }
    }
}