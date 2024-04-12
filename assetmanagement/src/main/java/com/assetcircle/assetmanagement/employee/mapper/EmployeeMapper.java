package com.assetcircle.assetmanagement.employee.mapper;

import com.assetcircle.assetmanagement.employee.dto.EmployeeDto;
import com.assetcircle.assetmanagement.employee.model.EmployeeModel;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface EmployeeMapper {

    EmployeeMapper INSTANCE = Mappers.getMapper(EmployeeMapper.class);

    EmployeeModel toEmployee (EmployeeDto employeeDto);

    EmployeeDto toEmployeeDto (EmployeeModel employeeModel);


}
