package com.assetmanagement.desklite.asset.service.impl;

import com.assetmanagement.desklite.asset.dto.ITAssetDTO;
import com.assetmanagement.desklite.asset.mapper.AssetMapper;
import com.assetmanagement.desklite.asset.models.ITAssetModel;
import com.assetmanagement.desklite.asset.repository.ITAssetRepository;
import com.assetmanagement.desklite.asset.service.ITAssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ITAssetServiceImpl implements ITAssetService {
    private final ITAssetRepository itAssetRepository;
    private final AssetMapper assetMapper;

    @Autowired
    public ITAssetServiceImpl(ITAssetRepository itAssetRepository, AssetMapper assetMapper) {
        this.itAssetRepository = itAssetRepository;
        this.assetMapper = assetMapper;
    }


    @Override
    public ITAssetDTO createITAsset(ITAssetDTO itAssetDTO) {
        log.info("Creating IT asset: {}", itAssetDTO);
        ITAssetModel itAssetModel = assetMapper.convertToITAssetEntity(itAssetDTO);
        ITAssetModel savedITAsset = itAssetRepository.save(itAssetModel);
        return assetMapper.convertToITAssetDTO(savedITAsset);
    }
}
