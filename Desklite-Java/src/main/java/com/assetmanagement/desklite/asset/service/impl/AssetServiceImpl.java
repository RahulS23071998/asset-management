package com.assetmanagement.desklite.asset.service.impl;

import com.assetmanagement.desklite.asset.dto.*;
import com.assetmanagement.desklite.asset.mapper.AssetMapper;
import com.assetmanagement.desklite.asset.models.AssetModel;
import com.assetmanagement.desklite.asset.models.FixedAssetModel;
import com.assetmanagement.desklite.asset.models.ITAssetModel;
import com.assetmanagement.desklite.asset.repository.AssetRepository;
import com.assetmanagement.desklite.asset.repository.FixedAssetRepository;
import com.assetmanagement.desklite.asset.repository.ITAssetRepository;
import com.assetmanagement.desklite.asset.service.AssetService;
import com.assetmanagement.desklite.asset.service.FixedAssetService;
import com.assetmanagement.desklite.asset.service.ITAssetService;
import com.assetmanagement.desklite.dashboard.exception.AssetNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public AssetDTO createAsset(AssetDTO assetDTO) {
        log.info("Creating asset: {}", assetDTO);
        AssetModel assetModel = assetMapper.convertToAssetEntity(assetDTO);
        AssetModel savedAsset = assetRepository.save(assetModel);
        return assetMapper.convertToAssetDTO(savedAsset);
    }

    @Override
    public AssetWithFixedAssetDTO postAssetWithFixedAsset(AssetDTO assetDTO, FixedAssetDTO fixedAssetDTO) {
        log.info("Posting asset with fixed asset. AssetDTO: {}, FixedAssetDTO: {}", assetDTO, fixedAssetDTO);
        AssetModel asset = assetMapper.convertToAssetEntity(assetDTO);
        FixedAssetModel fixedAsset = assetMapper.convertToFixedAssetEntity(fixedAssetDTO);

        asset = assetRepository.save(asset);

        fixedAsset.setAssetModel(asset);
        fixedAsset = fixedAssetRepository.save(fixedAsset);

        asset = assetRepository.findById(asset.getAssetId()).orElse(null);
        fixedAsset = fixedAssetRepository.findById(fixedAsset.getId()).orElse(null);

        if (asset == null || fixedAsset == null) {
            log.warn("Failed to post asset with fixed asset. Asset or fixed asset not found.");
            throw new AssetNotFoundException("Failed to post asset with fixed asset. Asset or fixed asset not found.");
        }

        return assetMapper.constructAssetWithFixedAssetDTO(assetMapper.convertToAssetDTO(asset), assetMapper.convertToFixedAssetDTO(fixedAsset));
    }

    @Override
    public AssetWithITAssetDTO postAssetWithITAsset(AssetDTO assetDTO, ITAssetDTO itAssetDTO) {
        log.info("Posting asset with IT asset. AssetDTO: {}, ITAssetDTO: {}", assetDTO, itAssetDTO);
        AssetModel asset = assetMapper.convertToAssetEntity(assetDTO);
        ITAssetModel itAsset = assetMapper.convertToITAssetEntity(itAssetDTO);

        asset = assetRepository.save(asset);

        itAsset.setAssetModel(asset);
        itAsset = itAssetRepository.save(itAsset);

        asset = assetRepository.findById(asset.getAssetId()).orElse(null);
        itAsset = itAssetRepository.findById(itAsset.getId()).orElse(null);

        if (asset == null || itAsset == null) {
            log.warn("Failed to post asset with IT asset. Asset or IT asset not found.");
            throw new AssetNotFoundException("Failed to post asset with IT asset. Asset or IT asset not found.");
        }

        return assetMapper.constructAssetWithITAssetDTO(assetMapper.convertToAssetDTO(asset), assetMapper.convertToITAssetDTO(itAsset));
    }

    @Override
    public List<AssetWithITAssetDTO> getAllAssetsWithITAssets() {
        log.info("Fetching all assets with IT assets.");
        List<AssetModel> assets = assetRepository.findAll();
        List<AssetWithITAssetDTO> assetWithITAssetDTOs = new ArrayList<>();

        for (AssetModel asset : assets) {
            ITAssetModel itAsset = itAssetRepository.findByAssetModel(asset);
            AssetDTO assetDTO = assetMapper.convertToAssetDTO(asset);
            ITAssetDTO itAssetDTO = (itAsset != null) ? assetMapper.convertToITAssetDTO(itAsset) : null;
            AssetWithITAssetDTO assetWithITAssetDTO = new AssetWithITAssetDTO(assetDTO, itAssetDTO);
            assetWithITAssetDTOs.add(assetWithITAssetDTO);
        }

        return assetWithITAssetDTOs;
    }

    @Override
    public List<AssetWithFixedAssetDTO> getAllAssetsWithFixedAssets() {
        log.info("Fetching all assets with fixed assets.");
        List<AssetModel> assets = assetRepository.findAll();
        List<AssetWithFixedAssetDTO> assetWithFixedAssetDTOs = new ArrayList<>();

        for (AssetModel asset : assets) {
            FixedAssetModel fixedAsset = fixedAssetRepository.findByAssetModel(asset);
            AssetDTO assetDTO = assetMapper.convertToAssetDTO(asset);
            FixedAssetDTO fixedAssetDTO = (fixedAsset != null) ? assetMapper.convertToFixedAssetDTO(fixedAsset) : null;
            AssetWithFixedAssetDTO assetWithFixedAssetDTO = new AssetWithFixedAssetDTO(assetDTO, fixedAssetDTO);
            assetWithFixedAssetDTOs.add(assetWithFixedAssetDTO);
        }

        return assetWithFixedAssetDTOs;
    }

    @Override
    public Optional<AssetWithITAndFixedDTO> getAssetWithITAndFixedByAssetCode(String assetCode) {
        log.info("Fetching asset with IT and fixed assets by asset code: {}", assetCode);
        Optional<AssetModel> assetModelOptional = assetRepository.findByAssetCode(assetCode);
        if (assetModelOptional.isPresent()) {
            AssetModel assetModel = assetModelOptional.get();
            Integer assetId = assetModel.getAssetId();

            AssetDTO assetDTO = assetMapper.convertToAssetDTO(assetModel);
            ITAssetModel itAssetModel = itAssetRepository.findByAssetId(assetId).orElse(null);
            FixedAssetModel fixedAssetModel = fixedAssetRepository.findByAssetId(assetId).orElse(null);

            ITAssetDTO itAssetDTO = (itAssetModel != null) ? assetMapper.convertToITAssetDTO(itAssetModel) : null;
            FixedAssetDTO fixedAssetDTO = (fixedAssetModel != null) ? assetMapper.convertToFixedAssetDTO(fixedAssetModel) : null;

            AssetWithITAndFixedDTO assetWithITAndFixedDTO = new AssetWithITAndFixedDTO();
            assetWithITAndFixedDTO.setAssetDTO(assetDTO);
            assetWithITAndFixedDTO.setItAssetDTO(itAssetDTO);
            assetWithITAndFixedDTO.setFixedAssetDTO(fixedAssetDTO);

            return Optional.of(assetWithITAndFixedDTO);
        } else {
            log.warn("Asset with code {} not found.", assetCode);
            return Optional.empty();
        }
    }
}