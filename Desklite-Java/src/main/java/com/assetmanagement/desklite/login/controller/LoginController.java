package com.assetmanagement.desklite.login.controller;


import com.assetmanagement.desklite.login.dto.AuthRequest;
import com.assetmanagement.desklite.login.dto.AuthResponseDTO;
import com.assetmanagement.desklite.login.dto.EmployeeDtoWithRolesDto;
import com.assetmanagement.desklite.login.models.EmployeeModel;
import com.assetmanagement.desklite.login.repository.IEmployeeDao;
import com.assetmanagement.desklite.login.service.JwtService;
import com.assetmanagement.desklite.login.service.UserService;

import com.assetmanagement.desklite.organization.service.OrganizationService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.extern.log4j.Log4j2;

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
	private IEmployeeDao iEmployeeDao;

	@Autowired
	private OrganizationService organizationService;

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
	public ResponseEntity<AuthResponseDTO> authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
		log.info("authenticateAndGetToken controller started");
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
		if (authentication.isAuthenticated()) {
			log.info("token generated");

			String generatedToken = jwtService.generateToken(authRequest.getEmail());
			log.info("token generated and sent back");

			Claims claims = Jwts.parserBuilder()
					.setSigningKey(jwtService.getSignKey())
					.build()
					.parseClaimsJws(generatedToken)
					.getBody();

			String workmail = claims.getSubject();

			EmployeeModel employee = iEmployeeDao.findByWorkmail(workmail);

			if (employee == null) {
				throw new UsernameNotFoundException("Employee not found for workmail: " + workmail);
			}

			String username = employee.getUsername();
			Long organizationId = employee.getOrganization().getOrganizationId();
			String organizationName = iEmployeeDao.findOrganizationNameByUsername(username);

			log.info("Organization name for user '{}' is '{}'", username, organizationName);

			AuthResponseDTO authResponse = new AuthResponseDTO(generatedToken, username, workmail, organizationName,organizationId);

			return ResponseEntity.ok().body(authResponse);
		} else {
			throw new UsernameNotFoundException("User not found !");
		}
	}



	@PostMapping("/userSignUp")
	    public EmployeeModel saveUser(@RequestBody EmployeeDtoWithRolesDto employeeDto){
	         EmployeeModel savedEmployeeModel = service.save(employeeDto);
	        return savedEmployeeModel;
	    }
	  
	  @GetMapping("/getAllEmployee")
	  public EmployeeModel getAllEmployee(@RequestParam String username){
		return service.findOne(username)  ;
		  
	  }

}
