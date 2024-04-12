package com.assetcircle.assetmanagement.asset.mapper;


import com.assetcircle.assetmanagement.asset.dto.*;
import com.assetcircle.assetmanagement.asset.model.AssetModel;
import com.assetcircle.assetmanagement.asset.model.FixedAssetModel;
import com.assetcircle.assetmanagement.asset.model.ITAssetModel;

public interface AssetMapper {
    AssetModel convertToAssetEntity(AssetDTO assetDTO);
    FixedAssetModel convertToFixedAssetEntity(FixedAssetDTO fixedAssetDTO);
    AssetDTO convertToAssetDTO(AssetModel assetModel);
    FixedAssetDTO convertToFixedAssetDTO(FixedAssetModel fixedAssetModel);
    AssetWithFixedAssetDTO constructAssetWithFixedAssetDTO(AssetDTO assetDTO, FixedAssetDTO fixedAssetDTO);
    AssetWithITAssetDTO constructAssetWithITAssetDTO(AssetDTO assetDTO, ITAssetDTO itAssetDTO);
    ITAssetModel convertToITAssetEntity(ITAssetDTO itAssetDTO);
    ITAssetDTO convertToITAssetDTO(ITAssetModel itAsset);
}