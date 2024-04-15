package com.assetmanagement.desklite.organization.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrganizationDto {

    @NotBlank (message = "Organization name is required")
    @Schema(
            description = "Organization Name",
            example = "Ladera Technology"
    )
    private String organizationName;

    @Email(message = "Invalid email format")
    @Schema(
            description = "Organization MailId",
            example = "mail@laderatechnology.com"
    )
    private String organizationEmailId;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "contactNumber must be 10 digits")
    @Schema(
            description = "Contact Number",
            example = "1234567890"
    )
    private String contactNumber;

    @Schema(
            description = "Location",
            example = "Chennai"
    )
    private String location;

    @NotBlank(message = "Organization code is required")
    @Schema(
            description = "Organization Code",
            example = "{OrganizationCode}{RandomNumber}"
    )
    private String organizationCode;

    @Schema(
            description = "Organization status"
    )
    private String status= "ACTIVE";

}
