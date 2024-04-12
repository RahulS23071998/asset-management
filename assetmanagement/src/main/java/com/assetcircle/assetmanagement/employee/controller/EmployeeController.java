package com.assetcircle.assetmanagement.employee.controller;

import com.assetcircle.assetmanagement.constant.ApplicationConstants;
import com.assetcircle.assetmanagement.employee.dto.EmployeeDto;
import com.assetcircle.assetmanagement.organization.dto.ResponseDto;
import com.assetcircle.assetmanagement.exception.EmployeeNotFoundException;
import com.assetcircle.assetmanagement.employee.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {
        "api/employee"
},produces = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE
})
@Tag(
        name = "EmployeeController",
        description = "Employee Controller for Request and Response"
)
@Validated
@Slf4j
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Operation(
            summary = "Get All Employees",
            description = "Retrieve all employees."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Successful retrieval of employees"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "No employees found"
            )
    })
    @GetMapping
    public ResponseEntity<List<EmployeeDto>> findAllEmployees() {
        List<EmployeeDto> employees = employeeService.getAllEmployees();
        if (!employees.isEmpty()) {
            return ResponseEntity.ok(employees);
        } else {
            throw new EmployeeNotFoundException("No Employee found");
        }
    }

    @Operation(
            summary = "Update Employee Details",
            description = "Update details of a specific employee by ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Employee details updated successfully"
            ),
            @ApiResponse(
                    responseCode = "417",
                    description = "Failed to update employee details"
            )
    })
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto> updateEmployeeDetails(@PathVariable("id") Long id,
                                                                 @RequestBody @Valid EmployeeDto employee) {
        boolean isUpdated = employeeService.updateEmployeeById(id, employee);
        if(isUpdated) {
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(new ResponseDto(ApplicationConstants.STATUS_200, ApplicationConstants.MESSAGE_200));
        }else{
            return ResponseEntity
                    .status(HttpStatus.EXPECTATION_FAILED)
                    .body(new ResponseDto(ApplicationConstants.STATUS_417, ApplicationConstants.MESSAGE_417_UPDATE));
        }
    }

    @DeleteMapping("/{employeeCode}")
    public ResponseEntity<ResponseDto> deleteEmployee(@PathVariable("employeeCode") String employeeCode) {
        boolean deleted = employeeService.disableEmployee(employeeCode);
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
