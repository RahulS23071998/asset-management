package com.assetmanagement.desklite.login.dto;

import java.util.Set;

import com.assetmanagement.desklite.login.models.EmployeeModel;
import com.assetmanagement.desklite.login.models.RoleModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDtoWithRolesDto {
	private String username;
	private String password;
	private String workmail;
	private String workdept;
	private int companyId;
	private Set<RoleModel> roles;

	public EmployeeModel getUserFromDto() {
		System.out.println("Workmail " + new EmployeeDtoWithRolesDto().getWorkmail());
		EmployeeModel employeeModel = new EmployeeModel();

		employeeModel.setUsername(username);
		employeeModel.setPassword(password);
		employeeModel.setWorkmail(workmail);
		employeeModel.setWorkdept(workdept);

		System.out.println(employeeModel);
		return employeeModel;
	}
}
