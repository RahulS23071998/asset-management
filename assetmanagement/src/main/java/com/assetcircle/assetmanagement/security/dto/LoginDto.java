package com.assetcircle.assetmanagement.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

    @Schema(
            description = "Username or Email",
            example = "johndoe or johndoe@example.com"
    )
    private String usernameOrEmail;

    @Schema(
            description = "Password"
    )
    private String password;

}
