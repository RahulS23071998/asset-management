package com.assetcircle.assetmanagement.employee.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {

    @Schema(
            description = "Role ID",
            example = "1"
    )
    private Long id;

    @Schema(
            description = "Role Name",
            example = "Admin"
    )
    private String name;

}
