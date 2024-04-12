package com.assetcircle.assetmanagement.security.service.impl;

import com.assetcircle.assetmanagement.security.config.JwtTokenProvider;
import com.assetcircle.assetmanagement.security.dto.LoginDto;
import com.assetcircle.assetmanagement.security.dto.RegisterDto;
import com.assetcircle.assetmanagement.exception.EmailAlreadyExistsException;
import com.assetcircle.assetmanagement.exception.InvalidStatusException;
import com.assetcircle.assetmanagement.exception.UserAlreadyExistsException;
import com.assetcircle.assetmanagement.employee.model.EmployeeModel;
import com.assetcircle.assetmanagement.enums.EmployeeStatus;
import com.assetcircle.assetmanagement.employee.model.Role;
import com.assetcircle.assetmanagement.employee.repository.EmployeeRepository;
import com.assetcircle.assetmanagement.employee.repository.RoleRepository;
import com.assetcircle.assetmanagement.security.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final EmployeeRepository employeeRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;

    public AuthServiceImpl(AuthenticationManager authenticationManager, EmployeeRepository employeeRepository,
                           RoleRepository roleRepository, PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider) {
        this.authenticationManager = authenticationManager;
        this.employeeRepository = employeeRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public String login(LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken
                (loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        Optional<EmployeeModel> employee = employeeRepository.findByUsernameOrEmailId(loginDto.getUsernameOrEmail(),loginDto.getUsernameOrEmail());

        if (employee.isPresent() && employee.get().getStatus() == EmployeeStatus.ACTIVE) {
            return jwtTokenProvider.generateToken(authentication);
        } else {

            return new InvalidStatusException("Employee Status is Inactive").toString();
        }
    }

    @Override
    public String register(RegisterDto registerDto) {

        if (employeeRepository.existsByUsername(registerDto.getUsername())){
            throw new UserAlreadyExistsException(HttpStatus.BAD_REQUEST,"username is already exist");
        }

        if (employeeRepository.existsByEmailId(registerDto.getEmailId())){
            throw new EmailAlreadyExistsException(HttpStatus.BAD_REQUEST,"email is already registered");
        }

        if (!registerDto.getStatus().equals("ACTIVE")) {
         log.warn("Invalid status '{}' specified for organization registration", registerDto.getStatus());
        throw new InvalidStatusException("Status must be set to 'ACTIVE' during Employee registration");
        }

        EmployeeModel employee = new EmployeeModel();

        employee.setFirstName(registerDto.getFirstName());
        employee.setLastName(registerDto.getLastName());
        employee.setUsername(registerDto.getUsername());
        employee.setEmailId(registerDto.getEmailId());
        employee.setDateOfBirth(registerDto.getDateOfBirth());
        employee.setDateOfJoining(registerDto.getDateOfJoining());
        employee.setDesignation(registerDto.getDesignation());
        employee.setContactNumber(registerDto.getContactNumber());
        employee.setEmployeeId(registerDto.getEmployeeId());
        employee.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        employee.setStatus(EmployeeStatus.ACTIVE);

        Set<Role> roles = new HashSet<>();
        Role userRole = roleRepository.findByName("ROLE_USER").get();
        roles.add(userRole);
        employee.setRoles(roles);

        employeeRepository.save(employee);
        return "User Registered Successfully";
    }
}
