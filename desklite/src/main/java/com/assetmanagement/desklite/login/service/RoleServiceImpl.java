package com.assetmanagement.desklite.login.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.assetmanagement.desklite.login.models.RoleModel;
import com.assetmanagement.desklite.login.repository.IRoleDao;


@Service
public class RoleServiceImpl implements IRoleService {
	
	  @Autowired
	    private IRoleDao roleDao;

	@Override
	public RoleModel findByName(String name) {
		return roleDao.findByName(name).orElse(null);
         
	}

}
