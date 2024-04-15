package com.assetmanagement.desklite.login.service;

import org.springframework.stereotype.Component;

import com.assetmanagement.desklite.login.models.RoleModel;


@Component
public interface IRoleService {
	RoleModel findByName(String name);
}
