package com.assetmanagement.desklite.login.mapper;

import com.assetmanagement.desklite.login.dto.EmployeeDTO;
import com.assetmanagement.desklite.login.models.EmployeeModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class EmployeeMapperImpl implements EmployeeMapper{
    @Override
    public EmployeeDTO toEmployeeDto(EmployeeModel employeeModel) {
        EmployeeDTO employeeDto = new EmployeeDTO();
        BeanUtils.copyProperties(employeeModel, employeeDto);
        return employeeDto;
    }

    @Override
    public EmployeeModel toEmployee(EmployeeDTO employeeDto) {
        EmployeeModel employeeModel = new EmployeeModel();
        BeanUtils.copyProperties(employeeDto, employeeModel);
        return employeeModel;
    }
}
