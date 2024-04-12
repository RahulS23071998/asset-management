package com.assetcircle.assetmanagement.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JWTAuthResponse {

    @Schema(
            description = "Access Token"
    )
    private String accessToken;

    @Schema(
            description = "Token Type",
            example = "Bearer"
    )
    private String tokenType = "Bearer";
}