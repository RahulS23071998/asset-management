package com.assetcircle.assetmanagement.security.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    @Schema(
            description = "First Name",
            example = "John"
    )
    @NotBlank(message = "First name is required")
    @Size(min = 2, max = 20, message = "First name must be between 2 and 50 characters")
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
    @NotBlank(message = "Username is required")
    @Size(min = 4, max = 20, message = "Username must be between 4 and 20 characters")
    private String username;

    @Schema(
            description = "Email ID",
            example = "john.doe@example.com"
    )
    @NotBlank(message = "Email ID is required")
    @Email(message = "Invalid email format")
    private String emailId;

    @Schema(
            description = "Employee ID",
            example = "EMP001"
    )
    @NotBlank(message = "Employee ID is required")
    private String employeeId;

    @Schema(
            description = "Password"
    )
    @NotBlank(message = "Password is required")
    @Size(min = 6, max = 20, message = "Password must be between 6 and 20 characters")
    private String password;

    @Schema(
            description = "Date of Joining",
            example = "2022-01-01"
    )
    private LocalDate dateOfJoining;

    @Schema(
            description = "Contact Number",
            example = "1234567890"
    )
    @NotBlank(message = "Contact number is required")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "contactNumber must be 10 digits")
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
    @NotBlank(message = "Designation is required")
    private String designation;

    @Schema(
            description = "Employee status"
    )
    private String status= "ACTIVE";
}
