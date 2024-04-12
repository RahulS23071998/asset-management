package com.assetcircle.assetmanagement.asset.service.impl;

import com.assetcircle.assetmanagement.asset.dto.*;
import com.assetcircle.assetmanagement.asset.mapper.AssetMapper;
import com.assetcircle.assetmanagement.asset.model.AssetModel;
import com.assetcircle.assetmanagement.asset.model.FixedAssetModel;
import com.assetcircle.assetmanagement.asset.model.ITAssetModel;
import com.assetcircle.assetmanagement.asset.repository.AssetRepository;
import com.assetcircle.assetmanagement.asset.repository.FixedAssetRepository;
import com.assetcircle.assetmanagement.asset.repository.ITAssetRepository;
import com.assetcircle.assetmanagement.asset.service.AssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class AssetServiceImpl implements AssetService {

    private final AssetRepository assetRepository;
    private final FixedAssetRepository fixedAssetRepository;
    private final ITAssetRepository itAssetRepository;
    private final AssetMapper assetMapper;

    @Autowired
    public AssetServiceImpl(AssetRepository assetRepository, FixedAssetRepository fixedAssetRepository, ITAssetRepository itAssetRepository, AssetMapper assetMapper) {
        this.assetRepository = assetRepository;
        this.fixedAssetRepository = fixedAssetRepository;
        this.itAssetRepository = itAssetRepository;
        this.assetMapper = assetMapper;
    }

    @Override
    public AssetModel createAsset(AssetModel assetModel) {
        return assetRepository.save(assetModel);
    }

    @Override
    public AssetWithFixedAssetDTO postAssetWithFixedAsset(AssetDTO assetDTO, FixedAssetDTO fixedAssetDTO) {

        AssetModel asset = assetMapper.convertToAssetEntity(assetDTO);
        FixedAssetModel fixedAsset = assetMapper.convertToFixedAssetEntity(fixedAssetDTO);

        asset = assetRepository.save(asset);

        fixedAsset.setAssetModel(asset);
        fixedAsset = fixedAssetRepository.save(fixedAsset);

        asset = assetRepository.findById(asset.getAssetId()).orElse(null);
        fixedAsset = fixedAssetRepository.findById(fixedAsset.getId()).orElse(null);
        return assetMapper.constructAssetWithFixedAssetDTO(assetMapper.convertToAssetDTO(asset), assetMapper.convertToFixedAssetDTO(fixedAsset));
    }

    @Override
    public AssetWithITAssetDTO postAssetWithITAsset(AssetDTO assetDTO, ITAssetDTO itAssetDTO) {
        AssetModel asset = assetMapper.convertToAssetEntity(assetDTO);
        ITAssetModel itAsset = assetMapper.convertToITAssetEntity(itAssetDTO);

        asset = assetRepository.save(asset);

        itAsset.setAssetModel(asset);
        itAsset = itAssetRepository.save(itAsset);

        asset = assetRepository.findById(asset.getAssetId()).orElse(null);
        itAsset = itAssetRepository.findById(itAsset.getId()).orElse(null);
        return assetMapper.constructAssetWithITAssetDTO(assetMapper.convertToAssetDTO(asset), assetMapper.convertToITAssetDTO(itAsset));
    }

    @Override
    public List<AssetWithITAssetDTO> getAllAssetsWithITAssets() {
        // Fetch all assets
        List<AssetModel> assets = assetRepository.findAll();

        // Create a list to store the response DTOs
        List<AssetWithITAssetDTO> assetWithITAssetDTOs = new ArrayList<>();

        // Iterate over each asset
        for (AssetModel asset : assets) {
            // Retrieve associated IT asset if available
            ITAssetModel itAsset = itAssetRepository.findByAssetModel(asset);

            // Convert models to DTOs
            AssetDTO assetDTO = assetMapper.convertToAssetDTO(asset);
            ITAssetDTO itAssetDTO = (itAsset != null) ? assetMapper.convertToITAssetDTO(itAsset) : null;

            // Create and add the response DTO
            AssetWithITAssetDTO assetWithITAssetDTO = new AssetWithITAssetDTO(assetDTO, itAssetDTO);
            assetWithITAssetDTOs.add(assetWithITAssetDTO);
        }

        return assetWithITAssetDTOs;
    }

    @Override
    public List<AssetWithFixedAssetDTO> getAllAssetsWithFixedAssets() {
        // Fetch all assets
        List<AssetModel> assets = assetRepository.findAll();

        // Create a list to store the response DTOs
        List<AssetWithFixedAssetDTO> assetWithFixedAssetDTOs = new ArrayList<>();

        for (AssetModel asset : assets) {
            // Retrieve associated Fixed  asset if available
            FixedAssetModel fixedAsset = fixedAssetRepository.findByAssetModel(asset);

            // Convert models to DTOs
            AssetDTO assetDTO = assetMapper.convertToAssetDTO(asset);
            FixedAssetDTO fixedAssetDTO = (fixedAsset != null) ? assetMapper.convertToFixedAssetDTO(fixedAsset) : null;

            // Create and add the response DTO
            AssetWithFixedAssetDTO assetWithFixedAssetDTO = new AssetWithFixedAssetDTO(assetDTO, fixedAssetDTO);
            assetWithFixedAssetDTOs.add(assetWithFixedAssetDTO);
        }
        return assetWithFixedAssetDTOs;
    }
}
