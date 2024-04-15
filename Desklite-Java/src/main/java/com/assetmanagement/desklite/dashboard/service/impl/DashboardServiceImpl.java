package com.assetmanagement.desklite.dashboard.service.impl;

import com.assetmanagement.desklite.asset.enums.AssetStatus;
import com.assetmanagement.desklite.asset.enums.AssetType;
import com.assetmanagement.desklite.asset.enums.AssignedStatus;
import com.assetmanagement.desklite.asset.models.AssetModel;
import com.assetmanagement.desklite.asset.repository.AssetRepository;
import com.assetmanagement.desklite.dashboard.dto.CountAndCostDTO;
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

        if (Arrays.stream(AssetStatus.values())
                .noneMatch(stat -> stat.name().equalsIgnoreCase(status))) {
            throw new IllegalArgumentException("Invalid status parameter. Allowed values: 'active' or 'inactive'");
        }

        // Fetch assets directly from the database using the provided status
        List<AssetModel> assets = assetRepository.findAllByAssetStatus(AssetStatus.valueOf(status.toUpperCase()));

        if (assets != null && !assets.isEmpty()) {
            log.info("Fetched {} assets", assets.size());

            // Calculate total cost of all fetched assets
            Double totalCost = getTotalCost(assets);

            // Count the number of assets with the specified status
            Long activeCount = (long) assets.size();

            // Create a DashboardDto for the specified status
            DashboardDto dashboardDto = new DashboardDto();
            dashboardDto.setTotalCost(totalCost);
            dashboardDto.setCount(activeCount);

            // Create a map to return the result
            Map<AssetStatus, DashboardDto> result = new HashMap<>();
            result.put(AssetStatus.valueOf(status.toUpperCase()), dashboardDto);

            return result;
        } else {
            log.warn("No assets found");
            return Collections.emptyMap();
        }
    }


    @Override
    public List<DashboardDto> getAllAsset() {
        List<AssetModel> allAssets = assetRepository.findAll();

        if (null != allAssets && !allAssets.isEmpty()) {
            log.info("Fetched {} assets", allAssets.size());

            Double totalCost = getTotalCost(allAssets);
            long totalCount = allAssets.size();

            DashboardDto allAssetsDashboardDto = new DashboardDto();
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
            Map<Year, Long> assetCountByFiscalYear = allAssets.stream()
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

    @Override
    public Map<String, CountAndCostDTO> getAssetCountAndSumForAssignedStatus(AssignedStatus status) {
        Map<String, CountAndCostDTO> assetInfo = new HashMap<>();

        if (status == null || status == AssignedStatus.ASSIGNED) {
            Long assignedCount = assetRepository.countByAssignedStatus(AssignedStatus.ASSIGNED);
            Double assignedCost = assetRepository.sumCostByAssignedStatus(AssignedStatus.ASSIGNED);
            assetInfo.put("assigned", new CountAndCostDTO(assignedCount, assignedCost));
        }

        if (status == null || status == AssignedStatus.NOT_ASSIGNED) {
            Long notAssignedCount = assetRepository.countByAssignedStatus(AssignedStatus.NOT_ASSIGNED);
            Double notAssignedCost = assetRepository.sumCostByAssignedStatus(AssignedStatus.NOT_ASSIGNED);
            assetInfo.put("notAssigned", new CountAndCostDTO(notAssignedCount, notAssignedCost));
        }

        return assetInfo;
    }


    @Override
    public Map<String, CountAndCostDTO> getAssetCountAndSumForAssetType(AssetType type) {
        Map<String, CountAndCostDTO> assetInfo = new HashMap<>();

        if (type == null || type == AssetType.PERMANENT) {
            Long permanentCount = assetRepository.countByAssetType(AssetType.PERMANENT);
            Double permanentCost = assetRepository.sumCostByAssetType(AssetType.PERMANENT);
            assetInfo.put("permanent", new CountAndCostDTO(permanentCount, permanentCost));
        }

        if (type == null || type == AssetType.LEASE) {
            Long leaseCount = assetRepository.countByAssetType(AssetType.LEASE);
            Double leaseCost = assetRepository.sumCostByAssetType(AssetType.LEASE);
            assetInfo.put("lease", new CountAndCostDTO(leaseCount, leaseCost));
        }

        return assetInfo;
    }
}
