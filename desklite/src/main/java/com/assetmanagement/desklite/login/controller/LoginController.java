package com.assetmanagement.desklite.login.controller;


import com.assetmanagement.desklite.login.dto.AuthRequest;
import com.assetmanagement.desklite.login.dto.EmployeeDtoWithRolesDto;
import com.assetmanagement.desklite.login.models.CompanyDetailsModel;
import com.assetmanagement.desklite.login.models.EmployeeModel;
import com.assetmanagement.desklite.login.service.CompanyService;
import com.assetmanagement.desklite.login.service.JwtService;
import com.assetmanagement.desklite.login.service.UserService;

import lombok.extern.log4j.Log4j2;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Log4j2
@CrossOrigin(originPatterns = "http://localhost:4200", methods = { RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT,
		RequestMethod.DELETE }, allowedHeaders = { "Content-type", "Authorization" })
public class LoginController {

	@Autowired
	private UserService service;
	
	@Autowired
	private CompanyService companyService;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@GetMapping("/welcome")
	public String welcome() {
		return "Welcome this endpoint is not secure";
	}

	@PostMapping("/addNewUser")
	public String addNewUser(@RequestBody EmployeeModel employeeInfo) {
		return service.addUser(employeeInfo);
	}

	@GetMapping("/user/userProfile")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public String userProfile() {
		return "Welcome to User Profile";
	}

	@GetMapping("/admin/adminProfile")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public String adminProfile() {
		return "Welcome to Admin Profile";
	}

	@PostMapping("/generateToken")
	public ResponseEntity<String> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		log.info("authenticateAndGetToken controller started");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			log.info("token generated");

			String generatedToken = jwtService.generateToken(authRequest.getUsername());
			log.info("token generated and sent back");
			return ResponseEntity.ok().body(generatedToken);
			

		} else {
			throw new UsernameNotFoundException("user not found !");
		}
	}
	
	
	  @PostMapping("/userSignUp")
	    public EmployeeModel saveUser(@RequestBody EmployeeDtoWithRolesDto employeeDto){
	         EmployeeModel savedEmployeeModel = service.save(employeeDto);
	        return savedEmployeeModel;
	    }
	  
	  @PostMapping("/companySignUp")
	    public CompanyDetailsModel saveCompany(@RequestBody CompanyDetailsModel companyDetails){
	         CompanyDetailsModel savedCompanyModel = companyService.saveCompany(companyDetails);
	        return savedCompanyModel;
	    }
	  
	  @GetMapping("/getAllEmployee")
	  public EmployeeModel getAllEmployee(@RequestParam String username){
		return service.findOne(username)  ;
		  
	  }

}
