package com.assetcircle.assetmanagement.employee.service;

import com.assetcircle.assetmanagement.employee.dto.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    List<EmployeeDto> getAllEmployees();

    boolean updateEmployeeById(Long id,EmployeeDto employeeDto);

    boolean disableEmployee(String employeeCode);


}
