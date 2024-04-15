package com.assetmanagement.desklite.dashboard.controller;

import com.assetmanagement.desklite.asset.enums.AssetType;
import com.assetmanagement.desklite.asset.enums.AssignedStatus;
import com.assetmanagement.desklite.dashboard.dto.CountAndCostDTO;
import com.assetmanagement.desklite.dashboard.dto.FiscalYearDto;
import com.assetmanagement.desklite.dashboard.exception.AssetNotFoundException;
import com.assetmanagement.desklite.dashboard.service.DashboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Year;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = {
        "api/dashboard"
},produces = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE
})
@Validated
@Slf4j
public class DashBoardController {

    private final DashboardService dashboardService;
    @Autowired
    public DashBoardController(DashboardService dashboardService) {
        this.dashboardService = dashboardService;
    }

    @GetMapping
    public ResponseEntity<?> findAllAssets(@RequestParam(required = false) String status) {
        Object assetData = (status != null && !status.isEmpty()) ? dashboardService.getAllAssetByStatus(status) : dashboardService.getAllAsset();

        if (assetData != null) {
            return ResponseEntity.ok(assetData);
        } else {
            throw new AssetNotFoundException("No assets found");
        }
    }

    @GetMapping("/fiscalyearcount")
    public ResponseEntity<?> getAssetCountByFiscalYear() {
        Map<Year, FiscalYearDto> assetCountByFiscalYear = dashboardService.getAssetDetailsByFiscalYear();
        return ResponseEntity.ok(assetCountByFiscalYear);
    }

    @GetMapping("/assets/status")
    public ResponseEntity<Map<String, CountAndCostDTO>> getTotalAvailableAssetsByAssignedStatus(@RequestParam(required = true) String status) {
        Map<String, CountAndCostDTO> assetInfo = new HashMap<>();

        if ("assigned".equalsIgnoreCase(status)) {
            assetInfo = dashboardService.getAssetCountAndSumForAssignedStatus(AssignedStatus.ASSIGNED);
        } else if ("notAssigned".equalsIgnoreCase(status)) {
            assetInfo = dashboardService.getAssetCountAndSumForAssignedStatus(AssignedStatus.NOT_ASSIGNED);
        }

        return ResponseEntity.ok(assetInfo);
    }

    @GetMapping("/asset/type")
    public ResponseEntity<Map<String, CountAndCostDTO>> getTotalAvailableAssetsByAssetType(@RequestParam(required = true) String type) {
        Map<String, CountAndCostDTO> assetInfo = new HashMap<>();

        if ("permanent".equalsIgnoreCase(type)) {
            assetInfo = dashboardService.getAssetCountAndSumForAssetType(AssetType.PERMANENT);
        } else if ("lease".equalsIgnoreCase(type)) {
            assetInfo = dashboardService.getAssetCountAndSumForAssetType(AssetType.LEASE);
        }

        return ResponseEntity.ok(assetInfo);
    }
}
