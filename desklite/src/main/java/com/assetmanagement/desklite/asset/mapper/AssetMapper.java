package com.assetmanagement.desklite.asset.mapper;

import com.assetmanagement.desklite.asset.dto.*;
import com.assetmanagement.desklite.asset.models.AssetModel;
import com.assetmanagement.desklite.asset.models.FixedAssetModel;
import com.assetmanagement.desklite.asset.models.ITAssetModel;
import com.assetmanagement.desklite.login.dto.EmployeeDTO;
import com.assetmanagement.desklite.login.models.EmployeeModel;

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
