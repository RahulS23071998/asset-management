package com.assetmanagement.desklite.asset.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AssetWithFixedAssetDTO {
    private AssetDTO asset;
    private FixedAssetDTO fixedAsset;
}