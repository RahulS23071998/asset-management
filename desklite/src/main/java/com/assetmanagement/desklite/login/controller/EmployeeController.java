package com.assetmanagement.desklite.login.controller;

import com.assetmanagement.desklite.asset.models.AssetModel;
import com.assetmanagement.desklite.login.dto.EmployeeAssetDTO;
import com.assetmanagement.desklite.login.dto.EmployeeDTO;
import com.assetmanagement.desklite.login.service.EmployeeAssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("api/employee")
public class EmployeeController {
    private final EmployeeAssetService employeeAssetService;

    @Autowired
    public EmployeeController(EmployeeAssetService employeeAssetService) {
        this.employeeAssetService = employeeAssetService;
    }

    @GetMapping("/assets")
    public EmployeeAssetDTO getEmployeeAndAssetsByUsername(@RequestParam String username) {
        return employeeAssetService.getEmployeeAndAssetsByUsername(username);
    }

}
