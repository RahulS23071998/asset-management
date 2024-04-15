package com.assetmanagement.desklite.asset.service;

import com.assetmanagement.desklite.asset.dto.*;
import com.assetmanagement.desklite.asset.models.AssetModel;
import java.util.List;
import java.util.Optional;

public interface AssetService {

    AssetDTO createAsset(AssetDTO assetDTO);
    AssetWithFixedAssetDTO postAssetWithFixedAsset(AssetDTO assetDTO, FixedAssetDTO fixedAssetDTO);

    AssetWithITAssetDTO postAssetWithITAsset(AssetDTO assetDTO, ITAssetDTO itAssetDTO);

    List<AssetWithITAssetDTO> getAllAssetsWithITAssets();

    List<AssetWithFixedAssetDTO> getAllAssetsWithFixedAssets();

    Optional<AssetWithITAndFixedDTO > getAssetWithITAndFixedByAssetCode(String assetCode);
}