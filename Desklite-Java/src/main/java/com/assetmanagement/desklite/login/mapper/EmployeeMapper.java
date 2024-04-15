package com.assetmanagement.desklite.login.mapper;

import com.assetmanagement.desklite.login.dto.EmployeeDTO;
import com.assetmanagement.desklite.login.models.EmployeeModel;

public interface EmployeeMapper {
    EmployeeDTO toEmployeeDto(EmployeeModel employeeModel);
    EmployeeModel toEmployee(EmployeeDTO employeeDto);
}
