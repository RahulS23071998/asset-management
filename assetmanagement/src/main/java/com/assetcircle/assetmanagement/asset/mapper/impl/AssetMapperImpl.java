package com.assetcircle.assetmanagement.asset.mapper.impl;

import com.assetcircle.assetmanagement.asset.dto.*;
import com.assetcircle.assetmanagement.asset.mapper.AssetMapper;
import com.assetcircle.assetmanagement.asset.model.AssetModel;
import com.assetcircle.assetmanagement.asset.model.FixedAssetModel;
import com.assetcircle.assetmanagement.asset.model.ITAssetModel;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class AssetMapperImpl implements AssetMapper {
    @Override
    public AssetModel convertToAssetEntity(AssetDTO assetDTO) {
        AssetModel assetModel = new AssetModel();
        BeanUtils.copyProperties(assetDTO, assetModel);
        return assetModel;
    }

    @Override
    public FixedAssetModel convertToFixedAssetEntity(FixedAssetDTO fixedAssetDTO) {
        FixedAssetModel fixedAssetModel = new FixedAssetModel();
        BeanUtils.copyProperties(fixedAssetDTO, fixedAssetModel);
        return fixedAssetModel;
    }

    @Override
    public AssetDTO convertToAssetDTO(AssetModel assetModel) {
        AssetDTO assetDTO = new AssetDTO();
        BeanUtils.copyProperties(assetModel, assetDTO);
        return assetDTO;
    }

    @Override
    public FixedAssetDTO convertToFixedAssetDTO(FixedAssetModel fixedAssetModel) {
        FixedAssetDTO fixedAssetDTO = new FixedAssetDTO();
        BeanUtils.copyProperties(fixedAssetModel, fixedAssetDTO);
        return fixedAssetDTO;
    }

    @Override
    public AssetWithFixedAssetDTO constructAssetWithFixedAssetDTO(AssetDTO assetDTO, FixedAssetDTO fixedAssetDTO) {
        AssetWithFixedAssetDTO assetWithFixedAssetDTO = new AssetWithFixedAssetDTO();
        assetWithFixedAssetDTO.setAsset(assetDTO);
        assetWithFixedAssetDTO.setFixedAsset(fixedAssetDTO);
        return assetWithFixedAssetDTO;
    }

    @Override
    public AssetWithITAssetDTO constructAssetWithITAssetDTO(AssetDTO assetDTO, ITAssetDTO itAssetDTO) {
        AssetWithITAssetDTO assetWithITAssetDTO = new AssetWithITAssetDTO();
        assetWithITAssetDTO.setAsset(assetDTO);
        assetWithITAssetDTO.setItAsset(itAssetDTO);
        return  assetWithITAssetDTO;
    }

    @Override
    public ITAssetModel convertToITAssetEntity(ITAssetDTO itAssetDTO) {
        ITAssetModel itAssetModel = new ITAssetModel();
        BeanUtils.copyProperties(itAssetDTO, itAssetModel);
        return itAssetModel;
    }

    @Override
    public ITAssetDTO convertToITAssetDTO(ITAssetModel itAssetModel) {
        ITAssetDTO itAssetDTO = new ITAssetDTO();
        BeanUtils.copyProperties(itAssetModel, itAssetDTO);
        return itAssetDTO;
    }
}