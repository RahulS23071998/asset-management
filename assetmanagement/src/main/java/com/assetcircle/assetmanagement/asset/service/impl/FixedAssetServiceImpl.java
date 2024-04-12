package com.assetcircle.assetmanagement.asset.service.impl;


import com.assetcircle.assetmanagement.asset.model.FixedAssetModel;
import com.assetcircle.assetmanagement.asset.repository.FixedAssetRepository;
import com.assetcircle.assetmanagement.asset.service.FixedAssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class FixedAssetServiceImpl implements FixedAssetService {

    private final FixedAssetRepository fixedAssetRepository;

    @Autowired
    public FixedAssetServiceImpl(FixedAssetRepository fixedAssetRepository) {
        this.fixedAssetRepository = fixedAssetRepository;
    }

    @Override
    public FixedAssetModel createFixedAsset(FixedAssetModel fixedAssetModel) {
        return fixedAssetRepository.save(fixedAssetModel);
    }
}
