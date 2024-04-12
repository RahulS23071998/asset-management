package com.assetcircle.assetmanagement.employee.service.impl;

import com.assetcircle.assetmanagement.employee.dto.EmployeeDto;
import com.assetcircle.assetmanagement.exception.EmployeeNotFoundException;
import com.assetcircle.assetmanagement.employee.mapper.EmployeeMapper;
import com.assetcircle.assetmanagement.employee.model.EmployeeModel;
import com.assetcircle.assetmanagement.enums.EmployeeStatus;
import com.assetcircle.assetmanagement.employee.repository.EmployeeRepository;
import com.assetcircle.assetmanagement.employee.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        log.info("Fetching all Employees");
        List<EmployeeModel> employees = employeeRepository.findAll();

        if (null != employees && !employees.isEmpty()) {
            log.info("Fetched {} employees", employees.size());
            return employees.stream()
                    .map(EmployeeMapper.INSTANCE::toEmployeeDto)
                    .collect(Collectors.toList());
        } else {
            log.warn("No employees found");
            return Collections.emptyList();
        }
    }

    @Override
    public boolean updateEmployeeById(Long id, EmployeeDto employeeDto) {
        boolean isUpdated = false;
        log.info("Updating Employee with ID: {}", id);
        if (Objects.isNull(id)) {
            throw new IllegalArgumentException("ID must not be null");
        }

        Optional<EmployeeModel> employeeOptional = employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            EmployeeModel employee = employeeOptional.get();
            log.info("Updating employee details: {}", employee);

            employee.setFirstName(employeeDto.getFirstName());
            employee.setLastName(employeeDto.getLastName());
            employee.setContactNumber(employeeDto.getContactNumber());
            employee.setEmailId(employeeDto.getEmailId());
            employee.setDesignation(employeeDto.getDesignation());

            EmployeeModel updatedEmployee = employeeRepository.save(employee);
            log.info("Employee updated successfully: {}", updatedEmployee);

            EmployeeMapper.INSTANCE.toEmployeeDto(updatedEmployee);
            isUpdated = true;
            return isUpdated;
        } else {
            log.warn("Employee with ID {} not found", id);
            throw new EmployeeNotFoundException("Employee not found");
        }
    }

    @Override
    public boolean disableEmployee(String employeeCode) {
        log.info("Deleting Employee with Code: {}", employeeCode);
        if (Objects.nonNull(employeeCode)) {
            Optional<EmployeeModel> employee = employeeRepository.findByEmployeeId(employeeCode);

            if (employee.isPresent()) {
                EmployeeModel employeeModel = employee.get();
                employee.get().setStatus(EmployeeStatus.INACTIVE);
                employeeRepository.save(employeeModel);

                log.info("Employee deleted successfully: {}", employee);
                return true;

            } else {
                log.warn("Employee with Id {} not found", employee);
                return false;
            }

        } else {
            throw new IllegalArgumentException("Employee Id must not be null");
        }
    }
}
