package com.assetmanagement.desklite.asset.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetWithITAndFixedDTO {
    private AssetDTO assetDTO;
    private ITAssetDTO itAssetDTO;
    private FixedAssetDTO fixedAssetDTO;
}