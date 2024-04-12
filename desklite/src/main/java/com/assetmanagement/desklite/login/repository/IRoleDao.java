package com.assetmanagement.desklite.login.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assetmanagement.desklite.login.models.RoleModel;


@Repository
public interface IRoleDao extends JpaRepository<RoleModel, Integer>{
	Optional<RoleModel> findByName(String name);       
}
