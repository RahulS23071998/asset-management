package com.assetmanagement.desklite.dashboard.service;

import com.assetmanagement.desklite.asset.enums.AssetStatus;
import com.assetmanagement.desklite.asset.enums.AssetType;
import com.assetmanagement.desklite.asset.enums.AssignedStatus;
import com.assetmanagement.desklite.asset.models.AssetModel;
import com.assetmanagement.desklite.dashboard.dto.CountAndCostDTO;
import com.assetmanagement.desklite.dashboard.dto.DashboardDto;
import com.assetmanagement.desklite.dashboard.dto.FiscalYearDto;

import java.time.Year;
import java.util.List;
import java.util.Map;

public interface DashboardService {

    Map<AssetStatus, DashboardDto> getAllAssetByStatus(String status);

    List<DashboardDto> getAllAsset();

    Map<Year, FiscalYearDto> getAssetDetailsByFiscalYear();

    Map<String, CountAndCostDTO> getAssetCountAndSumForAssignedStatus(AssignedStatus status);

    Map<String, CountAndCostDTO> getAssetCountAndSumForAssetType(AssetType type);

}
