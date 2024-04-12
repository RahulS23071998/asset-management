package com.assetcircle.assetmanagement.dashboard.service;


import com.assetcircle.assetmanagement.dashboard.dto.DashboardDto;
import com.assetcircle.assetmanagement.dashboard.dto.FiscalYearDto;
import com.assetcircle.assetmanagement.enums.AssetStatus;

import java.time.Year;
import java.util.List;
import java.util.Map;

public interface DashboardService {

    Map<AssetStatus, DashboardDto> getAllAssetByStatus(String status);

    List<DashboardDto> getAllAsset();

    Map<Year, FiscalYearDto> getAssetDetailsByFiscalYear();

}
