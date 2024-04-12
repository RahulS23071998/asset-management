package com.assetmanagement.desklite.dashboard.service.impl;

import com.assetmanagement.desklite.asset.enums.AssetStatus;
import com.assetmanagement.desklite.asset.models.AssetModel;
import com.assetmanagement.desklite.asset.repository.AssetRepository;
import com.assetmanagement.desklite.dashboard.dto.DashboardDto;
import com.assetmanagement.desklite.dashboard.dto.FiscalYearDto;
import com.assetmanagement.desklite.dashboard.service.DashboardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DashboardServiceImpl implements DashboardService {

    private final AssetRepository assetRepository;

    @Autowired
    public DashboardServiceImpl(AssetRepository assetRepository) {
        this.assetRepository = assetRepository;
    }

    @Override
    public Map<AssetStatus, DashboardDto> getAllAssetByStatus(String status) {
        log.info("Fetching assets with status: {}", status);

        List<AssetModel> assets;

        if (status != null && !status.isEmpty()) {
            if ("active".equalsIgnoreCase(status)) {
                assets= assetRepository.findAllByAssetStatus(AssetStatus.ACTIVE);
        } else if ("inactive".equalsIgnoreCase(status)) {
                assets = assetRepository.findAllByAssetStatus(AssetStatus.INACTIVE);
        } else {
                throw new IllegalArgumentException("Invalid status parameter. Allowed values: 'active' or 'inactive'");
        }

       if (null != assets && !assets.isEmpty()) {
                log.info("Fetched {} assets", assets.size());

           Double totalCost = getTotalCost(assets);

           Long activeCount= assets.stream()
                   .filter(asset -> asset.getAssetStatus() == AssetStatus.valueOf(status.toUpperCase()))
                   .count();

           DashboardDto dashboardDto = new DashboardDto();
           dashboardDto.setTotalCost(totalCost);
           dashboardDto.setCount(activeCount);


           Map<AssetStatus, DashboardDto> result = new HashMap<>();
           if ("active".equalsIgnoreCase(status)) {
               result.put(AssetStatus.ACTIVE, dashboardDto);
           } else if ("inactive".equalsIgnoreCase(status)) {
               result.put(AssetStatus.INACTIVE, dashboardDto);
           }

           return result;

       } else {
                log.warn("No assets found");
                return Collections.emptyMap();
            }
        }
        return Collections.emptyMap();

    }

    @Override
    public List<DashboardDto> getAllAsset() {
        List<AssetModel> allAssets = assetRepository.findAll();

        if (null != allAssets && !allAssets.isEmpty()) {
            log.info("Fetched {} assets", allAssets.size());

            Double totalCost = getTotalCost(allAssets);
            long totalCount = allAssets.size();

            DashboardDto allAssetsDashboardDto  = new DashboardDto();
            allAssetsDashboardDto.setTotalCost(totalCost);
            allAssetsDashboardDto.setCount(totalCount);

            return Collections.singletonList(allAssetsDashboardDto);
        } else {
            log.warn("No assets found");
            return Collections.emptyList();
        }
    }



    private static double getTotalCost(List<AssetModel> assets) {
        return assets.stream()
                .mapToDouble(AssetModel::getCost)
                .sum();
    }

    @Override
    public Map<Year, FiscalYearDto> getAssetDetailsByFiscalYear() {
        List<AssetModel> allAssets = assetRepository.findAll();

        if (allAssets != null && !allAssets.isEmpty()) {
            Map<Year, Long> assetCountByFiscalYear =  allAssets.stream()
                    .collect(Collectors.groupingBy(asset ->
                                    Year.from(asset.getPurchaseDate()),
                            Collectors.counting()));

            Map<Year, Double> totalCostByFiscalYear = allAssets.stream()
                    .collect(Collectors.groupingBy(asset ->
                                    Year.from(asset.getPurchaseDate()),
                            Collectors.summingDouble(AssetModel::getCost)));

            Map<Year, FiscalYearDto> assetDetailsByFiscalYear = assetCountByFiscalYear.entrySet()
                    .stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            entry -> new FiscalYearDto(entry.getKey().atDay(1),
                                    entry.getValue(),
                                    totalCostByFiscalYear.getOrDefault(entry.getKey(), 0.0)
                            )
                    ));

            log.info("Retrieved asset details by fiscal year: {}", assetDetailsByFiscalYear);
            return assetDetailsByFiscalYear;
        } else {
            log.info("No assets found, returning empty map");
            return Collections.emptyMap();
        }
    }
}
