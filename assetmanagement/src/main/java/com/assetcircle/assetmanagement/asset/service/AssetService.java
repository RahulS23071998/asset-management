package com.assetcircle.assetmanagement.asset.service;



import com.assetcircle.assetmanagement.asset.dto.*;
import com.assetcircle.assetmanagement.asset.model.AssetModel;

import java.util.List;

public interface AssetService {

    AssetModel createAsset (AssetModel assetModel);

    AssetWithFixedAssetDTO postAssetWithFixedAsset(AssetDTO assetDTO, FixedAssetDTO fixedAssetDTO);

    AssetWithITAssetDTO postAssetWithITAsset(AssetDTO assetDTO, ITAssetDTO itAssetDTO);

    List<AssetWithITAssetDTO> getAllAssetsWithITAssets();

    List<AssetWithFixedAssetDTO> getAllAssetsWithFixedAssets();
}