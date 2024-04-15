package com.assetmanagement.desklite.asset.service.impl;

import com.assetmanagement.desklite.asset.dto.FixedAssetDTO;
import com.assetmanagement.desklite.asset.mapper.AssetMapper;
import com.assetmanagement.desklite.asset.models.FixedAssetModel;
import com.assetmanagement.desklite.asset.repository.FixedAssetRepository;
import com.assetmanagement.desklite.asset.service.FixedAssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FixedAssetServiceImpl implements FixedAssetService {

    private final FixedAssetRepository fixedAssetRepository;
    private final AssetMapper assetMapper;

    @Autowired
    public FixedAssetServiceImpl(FixedAssetRepository fixedAssetRepository, AssetMapper assetMapper) {
        this.fixedAssetRepository = fixedAssetRepository;
        this.assetMapper = assetMapper;
    }

    @Override
    public FixedAssetDTO createFixedAsset(FixedAssetDTO fixedAssetDTO) {
        log.info("Creating fixed asset: {}", fixedAssetDTO);
        FixedAssetModel fixedAssetModel = assetMapper.convertToFixedAssetEntity(fixedAssetDTO);
        FixedAssetModel savedFixedAsset = fixedAssetRepository.save(fixedAssetModel);
        return assetMapper.convertToFixedAssetDTO(savedFixedAsset);
    }
}
