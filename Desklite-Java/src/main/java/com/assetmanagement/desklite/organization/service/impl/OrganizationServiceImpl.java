package com.assetmanagement.desklite.organization.service.impl;


import com.assetmanagement.desklite.exception.EmailAlreadyExistsException;
import com.assetmanagement.desklite.exception.InvalidStatusException;
import com.assetmanagement.desklite.exception.MobileNumberAlreadyExistsException;
import com.assetmanagement.desklite.exception.OrganizationNotFoundException;
import com.assetmanagement.desklite.organization.dto.OrganizationDto;
import com.assetmanagement.desklite.organization.mapper.OrganizationMapper;
import com.assetmanagement.desklite.organization.model.OrganizationModel;
import com.assetmanagement.desklite.organization.model.OrganizationStatus;
import com.assetmanagement.desklite.organization.repository.OrganizationRepository;
import com.assetmanagement.desklite.organization.service.OrganizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationRepository organizationRepository;
    private final OrganizationMapper organizationMapper;

    @Autowired
    public OrganizationServiceImpl(OrganizationRepository organizationRepository, OrganizationMapper organizationMapper) {
        this.organizationRepository = organizationRepository;
        this.organizationMapper = organizationMapper;
    }

    @Override
    public void registerOrganization(OrganizationDto organizationDto) {
        if (organizationDto != null) {
            log.info("Registering new organization: {}", organizationDto);

            if (!organizationDto.getStatus().equals("ACTIVE")) {
                log.warn("Invalid status '{}' specified for organization registration", organizationDto.getStatus());
                throw new InvalidStatusException("Status must be set to 'ACTIVE' during organization registration");
            }

            organizationRepository.findByOrganizationEmailId(organizationDto.getOrganizationEmailId())
                    .ifPresent(existingOrganization -> {
                        log.warn("Organization with email '{}' already exists", organizationDto.getOrganizationEmailId());
                        throw new EmailAlreadyExistsException(HttpStatus.BAD_REQUEST, "Organization with the given email already exists");
                    });

            if (organizationRepository.existsByContactNumber(organizationDto.getContactNumber())){
                log.warn("Mobile number already exists for organization: {}", organizationDto.getContactNumber());
                throw new MobileNumberAlreadyExistsException("Organization with the given mobile number already exists");
            }

            OrganizationModel organizationModel = organizationMapper.toOrganization(organizationDto);
            OrganizationModel registeredOrganization = organizationRepository.save(organizationModel);

            log.info("Organization registered successfully: {}", registeredOrganization);

            organizationMapper.toOrganizationDto(registeredOrganization);
        } else {
            throw new IllegalArgumentException("organizationDto must not be null");
        }
    }

    @Override
    public Optional<OrganizationDto> getOrganizationByCode(String organizationCode) {
        Optional<OrganizationModel> byOrganizationCode = organizationRepository.findByOrganizationCode(organizationCode);
        if (byOrganizationCode.isPresent()){
            log.info("Organization found with code: {}", organizationCode);
            return Optional.ofNullable(byOrganizationCode.map(organizationMapper::toOrganizationDto)
                    .orElseThrow(() -> new OrganizationNotFoundException("Organization Code Not Found")));
        }else {
            log.warn("Organization not found with code: {}", organizationCode);
            return Optional.empty();
        }
    }

    @Override
    public Map<String,List<OrganizationDto>> getAllOrganization(String status) {
        log.info("Fetching organizations with status: {}", status);
        List<OrganizationModel> organizations;

        if (status != null && !status.isEmpty()) {
            if ("active".equalsIgnoreCase(status)) {
                organizations = organizationRepository.findAllByStatus(OrganizationStatus.ACTIVE);
            } else if ("inactive".equalsIgnoreCase(status)) {
                organizations = organizationRepository.findAllByStatus(OrganizationStatus.INACTIVE);
            } else {
                throw new IllegalArgumentException("Invalid status parameter. Allowed values: 'active' or 'inactive'");
            }

            if (null != organizations && !organizations.isEmpty()) {
                log.info("Fetched {} organizations", organizations.size());
                return organizations.stream()
                        .map(organizationMapper::toOrganizationDto)
                        .collect(Collectors.groupingBy(OrganizationDto::getStatus));
            } else {
                log.warn("No organizations found");
                return Collections.emptyMap();
            }
        }
        return Collections.emptyMap();
    }

    @Override
    public List<OrganizationDto> getAllOrganization() {
            List<OrganizationModel> allOrganizations = organizationRepository.findAll();
            if (null != allOrganizations  && !allOrganizations.isEmpty()) {
                log.info("Fetched {} organizations", allOrganizations.size());
                return allOrganizations.stream()
                        .map(organizationMapper::toOrganizationDto)
                        .collect(Collectors.toList());
            } else {
                log.warn("No organizations found");
                return Collections.emptyList();
            }
    }


    @Override
    public boolean updateOrganization(Long id, OrganizationDto organizationDto) {
        boolean isUpdated = false;
        log.info("Updating Organization with ID: {}", id);
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("ID must not be null");
        }

        Optional<OrganizationModel> organizationOptional = organizationRepository.findById(id);
        if (organizationOptional.isPresent()) {
            OrganizationModel organization = organizationOptional.get();
            log.info("Updating organization details: {}", organization);

            organization.setOrganizationCode(organizationDto.getOrganizationCode());
            organization.setOrganizationEmailId(organizationDto.getOrganizationEmailId());
            organization.setLocation(organizationDto.getLocation());
            organization.setContactNumber(organizationDto.getContactNumber());
            organization.setOrganizationName(organizationDto.getOrganizationName());

            OrganizationModel updatedOrganization = organizationRepository.save(organization);
            log.info("Organization updated successfully: {}", updatedOrganization);

            organizationMapper.toOrganizationDto(updatedOrganization);
             isUpdated=true;
             return isUpdated;
        } else {
            log.warn("Organization with ID {} not found", id);
            throw new OrganizationNotFoundException("Organization not found");
        }
    }

    @Override
    public boolean disableOrganization(String organizationCode) {
        log.info("Deleting organization with Code: {}", organizationCode);
        if (Objects.nonNull(organizationCode)) {
            Optional<OrganizationModel> organization = organizationRepository.findByOrganizationCode(organizationCode);

            if (organization.isPresent()) {
                OrganizationModel organizationModel = organization.get();
                organization.get().setStatus(OrganizationStatus.INACTIVE);
                organizationRepository.save(organizationModel);

                log.info("Organization deleted successfully: {}", organization);
                return true;

            } else {
                log.warn("Organization with code {} not found", organizationCode);
                return false;
            }

        } else {
            throw new IllegalArgumentException("Organization code must not be null");
        }
    }
}
