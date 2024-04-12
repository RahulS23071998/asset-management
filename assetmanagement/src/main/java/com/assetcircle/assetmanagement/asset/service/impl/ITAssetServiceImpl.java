package com.assetcircle.assetmanagement.asset.service.impl;


import com.assetcircle.assetmanagement.asset.model.ITAssetModel;
import com.assetcircle.assetmanagement.asset.repository.ITAssetRepository;
import com.assetcircle.assetmanagement.asset.service.ITAssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ITAssetServiceImpl implements ITAssetService {
    private final ITAssetRepository itAssetRepository;

    @Autowired
    public ITAssetServiceImpl(ITAssetRepository itAssetRepository) {
        this.itAssetRepository = itAssetRepository;
    }


    @Override
    public ITAssetModel createITAsset(ITAssetModel itAssetModel) {
        return itAssetRepository.save(itAssetModel);
    }
}
