package com.assetmanagement.desklite.login.service;

import java.util.List;
import java.util.Optional;

import com.assetmanagement.desklite.login.dto.EmployeeDtoWithRolesDto;
import org.springframework.stereotype.Component;

import com.assetmanagement.desklite.login.models.EmployeeModel;


@Component
public interface IUserService {
	 	EmployeeModel save(EmployeeDtoWithRolesDto employeeDto);
	    List<EmployeeModel> findAll();
	    EmployeeModel findOne(String username);
}
