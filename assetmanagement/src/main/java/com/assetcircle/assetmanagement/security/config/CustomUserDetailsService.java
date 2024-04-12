package com.assetcircle.assetmanagement.security.config;

import com.assetcircle.assetmanagement.employee.model.EmployeeModel;
import com.assetcircle.assetmanagement.employee.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    private final EmployeeRepository employeeRepository;
    @Autowired
    public CustomUserDetailsService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {

        EmployeeModel employee = employeeRepository.findByUsernameOrEmailId(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email " + usernameOrEmail));

        Set<GrantedAuthority> authorities = employee.getRoles()
                .stream().map(role -> {
                    return new SimpleGrantedAuthority(role.getName());
                }).collect(Collectors.toSet());

        return new User(employee.getEmailId(),employee.getPassword(),authorities);
    }
}
