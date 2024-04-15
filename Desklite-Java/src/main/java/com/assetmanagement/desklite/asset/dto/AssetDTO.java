package com.assetmanagement.desklite.asset.dto;

import com.assetmanagement.desklite.asset.enums.AssetStatus;
import com.assetmanagement.desklite.asset.enums.AssetType;
import com.assetmanagement.desklite.asset.enums.AssignedStatus;
import com.assetmanagement.desklite.asset.enums.OperationalStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AssetDTO {

    private Integer assetId;
    private String assetName;
    private String description;
    private String serialNumber;
    private String modelNumber;
    private String brand;
    private double cost;
    private String assetCode;
    private LocalDate purchaseDate;
    private AssignedStatus assignedStatus;
    private OperationalStatus operationalStatus;
    private AssetType assetType;
    private AssetStatus assetStatus;
}
