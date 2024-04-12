package com.assetcircle.assetmanagement.asset.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssetWithITAssetDTO {
    private AssetDTO asset;
    private ITAssetDTO itAsset;
}