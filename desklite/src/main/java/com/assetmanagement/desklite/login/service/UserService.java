package com.assetmanagement.desklite.login.service;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.assetmanagement.desklite.login.dto.EmployeeDtoWithRolesDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.assetmanagement.desklite.login.models.CompanyDetailsModel;
import com.assetmanagement.desklite.login.models.EmployeeModel;
import com.assetmanagement.desklite.login.models.RoleModel;
import com.assetmanagement.desklite.login.repository.ICompanyDao;
import com.assetmanagement.desklite.login.repository.IEmployeeDao;
import com.assetmanagement.desklite.login.repository.IUserInfo; 
  
@Service
@Component
public class UserService implements UserDetailsService,IUserService {
	
	
	@Autowired
	private IRoleService roleService;
	
	@Autowired
	private IEmployeeDao employeeDao;
	
	@Autowired 
	private ICompanyDao companyDao;
	
	@Autowired
    private IUserInfo repository; 
  
    @Autowired
    private PasswordEncoder encoder; 
  
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException { 
  
        Optional<EmployeeModel> userDetail = repository.findByUsername(username); 
  
        // Converting userDetail to UserDetails 
        return userDetail.map(UserInfoDetails::new) 
                .orElseThrow(() -> new UsernameNotFoundException("User not found " + username)); 
    } 
  
    public String addUser(EmployeeModel employeeInfo) { 
    	employeeInfo.setPassword(encoder.encode(employeeInfo.getPassword())); 
        repository.save(employeeInfo); 
        return "User Added Successfully"; 
    }
    
    
    private Set<SimpleGrantedAuthority> getAuthority(EmployeeModel employeeModel) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        employeeModel.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        });
        return authorities;
    }
    
    
    
    @Override
    public List<EmployeeModel> findAll() {
        List<EmployeeModel> list = new ArrayList<>();
        employeeDao.findAll().iterator().forEachRemaining(list::add);
        return list;
    }

	@Override
	public EmployeeModel save(EmployeeDtoWithRolesDto employeeDto) {
		EmployeeModel nUser = employeeDto.getUserFromDto();
//	    employeeDto.getRoles().forEach(e->System.out.println(e.getName()));
        nUser.setPassword(encoder.encode(employeeDto.getPassword()));

        RoleModel roleModel = roleService.findByName("USER");
        Set<RoleModel> roleSet = new HashSet<>();
        roleSet.add(roleModel);

//        if(nUser.getWorkmail().split("@")[1].equals("admin.edu")){
//        	roleModel = roleService.findByName("ADMIN");
//            roleSet.add(roleModel);
//        }

        nUser.setRoles(roleSet);
        
      CompanyDetailsModel foundedCompany = companyDao.findById(employeeDto.getCompanyId()).orElse(null);
        nUser.setCompanyId(foundedCompany);
        EmployeeModel savedEmployee = employeeDao.save(nUser);
        return savedEmployee;
	}

	@Override
	public EmployeeModel findOne(String username) {
		return employeeDao.findByUsername(username);
	} 

}

