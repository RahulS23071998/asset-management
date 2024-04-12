package com.assetmanagement.desklite.login.dto;

import com.assetmanagement.desklite.asset.dto.AssetDTO;
import com.assetmanagement.desklite.asset.dto.AssetWithITAndFixedDTO;
import com.assetmanagement.desklite.asset.dto.FixedAssetDTO;
import com.assetmanagement.desklite.asset.dto.ITAssetDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeAssetDTO {
   private EmployeeDTO employeeDto;
   private AssetDTO assetDTO;
   private FixedAssetDTO fixedAssetDTO;
   private ITAssetDTO itAssetDTO;
}
