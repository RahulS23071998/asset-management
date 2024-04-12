package com.assetcircle.assetmanagement.asset.dto;


import com.assetcircle.assetmanagement.enums.AssetStatus;
import com.assetcircle.assetmanagement.enums.AssetType;
import com.assetcircle.assetmanagement.enums.AssignedStatus;
import com.assetcircle.assetmanagement.enums.OperationalStatus;
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
    private LocalDate purchaseDate;
    private AssignedStatus assignedStatus;
    private OperationalStatus operationalStatus;
    private AssetType assetType;
    private AssetStatus assetStatus;
}
