package com.assetcircle.assetmanagement.employee.dto;

import com.assetcircle.assetmanagement.enums.EmployeeStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

    @Schema(
            description = "First Name",
            example = "John"
    )
    private String firstName;

    @Schema(
            description = "Last Name",
            example = "Doe"
    )
    private String lastName;

    @Schema(
            description = "Username",
            example = "johndoe"
    )
    private String username;

    @Schema(
            description = "Email ID",
            example = "john.doe@example.com"
    )
    private String emailId;

    @Schema(
            description = "Employee ID",
            example = "E12345"
    )
    private String employeeId;

    @Schema(
            description = "Date of Joining",
            example = "2022-01-01"
    )
    private LocalDate dateOfJoining;

    @Schema(
            description = "Contact Number",
            example = "1234567890"
    )
    private String contactNumber;

    @Schema(
            description = "Date of Birth",
            example = "1990-01-01"
    )
    private LocalDate dateOfBirth;

    @Schema(
            description = "Designation",
            example = "Software Engineer"
    )
    private String designation;

    @Schema(
            description = "Employee status"
    )
    private EmployeeStatus status ;

    @Schema(
            description = "Roles assigned to the employee"
    )
    private Set<RoleDto> roles;
}
