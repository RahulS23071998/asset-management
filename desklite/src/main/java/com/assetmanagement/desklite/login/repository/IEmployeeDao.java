package com.assetmanagement.desklite.login.repository;

import com.assetmanagement.desklite.asset.models.AssetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assetmanagement.desklite.login.models.EmployeeModel;

import java.util.List;
import java.util.Optional;

@Repository
public interface IEmployeeDao extends JpaRepository<EmployeeModel,Integer  > {
  EmployeeModel findByUsername(String username);
}
