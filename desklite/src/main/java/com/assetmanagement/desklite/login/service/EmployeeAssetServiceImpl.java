package com.assetmanagement.desklite.login.service;


import com.assetmanagement.desklite.asset.dto.AssetDTO;
import com.assetmanagement.desklite.asset.dto.FixedAssetDTO;
import com.assetmanagement.desklite.asset.dto.ITAssetDTO;
import com.assetmanagement.desklite.asset.mapper.AssetMapper;
import com.assetmanagement.desklite.asset.models.AssetModel;
import com.assetmanagement.desklite.asset.models.FixedAssetModel;
import com.assetmanagement.desklite.asset.models.ITAssetModel;
import com.assetmanagement.desklite.asset.repository.AssetRepository;
import com.assetmanagement.desklite.asset.repository.FixedAssetRepository;
import com.assetmanagement.desklite.asset.repository.ITAssetRepository;
import com.assetmanagement.desklite.dashboard.exception.AssetNotFoundException;
import com.assetmanagement.desklite.exception.EmployeeNotFoundException;
import com.assetmanagement.desklite.login.dto.EmployeeAssetDTO;
import com.assetmanagement.desklite.login.dto.EmployeeDTO;
import com.assetmanagement.desklite.login.models.EmployeeModel;
import com.assetmanagement.desklite.login.repository.IEmployeeDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Slf4j
public class EmployeeAssetServiceImpl implements EmployeeAssetService {
    private final IEmployeeDao iEmployeeDao;
    private final AssetMapper assetMapper;
    private final ITAssetRepository itAssetRepository;
    private final FixedAssetRepository fixedAssetRepository;
    private final AssetRepository assetRepository;

    public EmployeeAssetServiceImpl(IEmployeeDao iEmployeeDao, AssetMapper assetMapper, ITAssetRepository itAssetRepository, FixedAssetRepository fixedAssetRepository, AssetRepository assetRepository) {
        this.iEmployeeDao = iEmployeeDao;
        this.assetMapper = assetMapper;
        this.itAssetRepository = itAssetRepository;
        this.fixedAssetRepository = fixedAssetRepository;
        this.assetRepository = assetRepository;
    }

    @Override
    public EmployeeAssetDTO getEmployeeAndAssetsByUsername(String username) {
        log.info("Fetching employee and associated assets information for username: {}", username);

        EmployeeModel employee = iEmployeeDao.findByUsername(username);

        if (employee == null) {
            log.warn("Employee with username {} not found", username);
            throw new EmployeeNotFoundException("Employee is not found");
        }

        List<AssetModel> assets = assetRepository.findByEmployee(employee);

        EmployeeAssetDTO employeeAssetDTO = new EmployeeAssetDTO();
        employeeAssetDTO.setEmployeeDto(new EmployeeDTO(employee.getUsername(), employee.getWorkmail(), employee.getWorkdept()));

        if (assets != null && !assets.isEmpty()) {
            AssetModel asset = assets.get(0);

            AssetDTO assetDTO = assetMapper.convertToAssetDTO(asset);
            employeeAssetDTO.setAssetDTO(assetDTO);

            Integer assetId = asset.getAssetId();
            ITAssetModel itAssetModel = itAssetRepository.findByAssetId(assetId).orElse(null);
            FixedAssetModel fixedAssetModel = fixedAssetRepository.findByAssetId(assetId).orElse(null);

            ITAssetDTO itAssetDTO = (itAssetModel != null) ? assetMapper.convertToITAssetDTO(itAssetModel) : null;
            FixedAssetDTO fixedAssetDTO = (fixedAssetModel != null) ? assetMapper.convertToFixedAssetDTO(fixedAssetModel) : null;

            employeeAssetDTO.setItAssetDTO(itAssetDTO);
            employeeAssetDTO.setFixedAssetDTO(fixedAssetDTO);
        } else {
            log.warn("No assets found for employee with username {}", username);
            throw new AssetNotFoundException("Asset is not found with the employee "+ username);
        }
        log.info("Returning employee and associated assets information for username: {}", username);
        return employeeAssetDTO;
    }

}
