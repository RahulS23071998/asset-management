package com.assetmanagement.desklite.login.service;

import com.assetmanagement.desklite.login.dto.EmployeeAssetDTO;

public interface EmployeeAssetService {

    EmployeeAssetDTO getEmployeeAndAssetsByUsername(String username);
}
