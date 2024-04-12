package com.assetmanagement.desklite.login.dto;

import com.assetmanagement.desklite.login.models.CompanyDetailsModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {
    private String username;
    private String workmail;
    private String workdept;
}
