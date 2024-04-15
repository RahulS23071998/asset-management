package com.assetmanagement.desklite.organization.controller;


import com.assetmanagement.desklite.constant.ApplicationConstants;
import com.assetmanagement.desklite.exception.OrganizationNotFoundException;
import com.assetmanagement.desklite.organization.dto.ErrorResponseDto;
import com.assetmanagement.desklite.organization.dto.OrganizationDto;
import com.assetmanagement.desklite.organization.dto.ResponseDto;
import com.assetmanagement.desklite.organization.service.OrganizationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@Tag(
        name = "OrganizationController",
        description = "Organization Controller for Request and Response"
)
@Slf4j
@Validated
@RequestMapping(value = "/api/organization",produces = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE
})
public class OrganizationController {

    private final OrganizationService organizationService;
    @Autowired
    public OrganizationController(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @Operation(
            summary = "Register Organization REST API",
            description = "REST API to Register new Organization "
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "HTTP Status CREATED"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PostMapping("/register")
    public ResponseEntity<ResponseDto> registerOrganization(@Valid @RequestBody OrganizationDto organizationDto) {
        organizationService.registerOrganization(organizationDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ResponseDto(ApplicationConstants.STATUS_201, ApplicationConstants.MESSAGE_201));
    }

    @Operation(
            summary = "Get Organization by Code",
            description = "Retrieves organization details based on its code."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful retrieval of organization",
                    content = @Content(
                            schema = @Schema(implementation = OrganizationDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Organization not found"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping("/{code}")
    public ResponseEntity<OrganizationDto> getOrganization(@PathVariable("code") String organizationCode) {
        Optional<OrganizationDto> organization = organizationService.getOrganizationByCode(organizationCode);
        if (organization.isPresent()) {
            OrganizationDto organizationDto = organization.get();
            log.info("Organization found with code: {}", organizationCode);
            return ResponseEntity.ok(organizationDto);
        } else {
            log.warn("Organization not found with code: {}", organizationCode);
            throw new OrganizationNotFoundException("Organization not found");
        }
    }


    @Operation(
            summary = "Find all organizations",
            description = "Returns a list of all organizations."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful retrieval of organizations",
                    content = @Content(
                            schema = @Schema(implementation = OrganizationDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No organizations found"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @GetMapping
    public ResponseEntity<?> findAllOrganization(@RequestParam(required = false) String status) {
        Object organizationData = (status != null && !status.isEmpty()) ? organizationService.getAllOrganization(status) : organizationService.getAllOrganization();

        if (organizationData != null) {
            return ResponseEntity.ok(organizationData);
        } else {
            throw new OrganizationNotFoundException("No organizations found");
        }
    }

    @Operation(
            summary = "Update Organization Details REST API",
            description = "REST API to update Organization details"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "HTTP Status OK"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Expectation Failed"
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "HTTP Status Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateOrganizationDetails(@PathVariable("id") Long id,
                                                @RequestBody @Valid OrganizationDto organizationDto) {
        boolean isUpdated = organizationService.updateOrganization(id, organizationDto);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(ApplicationConstants.STATUS_200, ApplicationConstants.MESSAGE_200));
        } else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(ApplicationConstants.STATUS_417, ApplicationConstants.MESSAGE_417_UPDATE));
        }
    }

    @Operation(
            summary = "Delete Organization",
            description = "Deletes an organization based on its organization code."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Organization deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Organization not found",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Internal Server Error",
                    content = @Content(
                            schema = @Schema(implementation = ErrorResponseDto.class)
                    )
            )
    })
    @DeleteMapping("/{organizationCode}")
    public ResponseEntity<ResponseDto> deleteOrganization(@PathVariable("organizationCode") String organizationCode) {
        boolean deleted = organizationService.disableOrganization(organizationCode);
        if (deleted) {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(new ResponseDto(ApplicationConstants.STATUS_200,ApplicationConstants.MESSAGE_200));
        }else {
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(ApplicationConstants.STATUS_417, ApplicationConstants.MESSAGE_417_DELETE));
        }
    }
}

