package com.assetmanagement.desklite.login.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponseDTO {

    @Schema(
            description = "Access Token"
    )
    private String accessToken;

    @Schema(description = "Username")
    private String username;

    @Schema(description = "Email")
    private String email;

    private String organizationName;

    private Long organizationId;
}
