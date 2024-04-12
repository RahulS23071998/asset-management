package com.assetmanagement.desklite.login.repository;


import com.assetmanagement.desklite.login.models.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository; 
 
import java.util.Optional; 


@Repository
@Component
public interface IUserInfo extends JpaRepository<EmployeeModel, Integer> { 
    Optional<EmployeeModel> findByUsername(String username); 
}
