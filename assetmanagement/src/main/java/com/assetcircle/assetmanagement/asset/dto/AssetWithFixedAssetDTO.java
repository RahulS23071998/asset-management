package com.assetcircle.assetmanagement.asset.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssetWithFixedAssetDTO {
    private AssetDTO asset;
    private FixedAssetDTO fixedAsset;
}