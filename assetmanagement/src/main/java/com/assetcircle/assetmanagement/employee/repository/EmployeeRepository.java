package com.assetcircle.assetmanagement.employee.repository;

import com.assetcircle.assetmanagement.employee.model.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel,Long> {

    Optional<EmployeeModel> findByEmailId(String email);
    Optional<EmployeeModel> findByUsernameOrEmailId(String username,String email);
    Optional<EmployeeModel> findByUsername(String username);
    Boolean existsByUsername(String username);
    Boolean existsByEmailId(String email);
    Optional<EmployeeModel> findByEmployeeId(String employeeId);

}
