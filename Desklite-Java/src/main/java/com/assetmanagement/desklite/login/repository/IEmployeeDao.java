package com.assetmanagement.desklite.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.assetmanagement.desklite.login.models.EmployeeModel;

@Repository
public interface IEmployeeDao extends JpaRepository<EmployeeModel,Integer> {
  EmployeeModel findByUsername(String username);

  EmployeeModel findByWorkmail(String workmail);
  @Query("SELECT e.organization.organizationName FROM EmployeeModel e WHERE e.username = :username")
  String findOrganizationNameByUsername(@Param("username") String username);


}

