package com.assetmanagement.desklite.asset.repository;

import com.assetmanagement.desklite.asset.enums.AssetStatus;
import com.assetmanagement.desklite.asset.enums.AssetType;
import com.assetmanagement.desklite.asset.enums.AssignedStatus;
import com.assetmanagement.desklite.asset.models.AssetModel;
import com.assetmanagement.desklite.login.models.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetRepository extends JpaRepository<AssetModel,Integer> {
    List<AssetModel> findAllByAssetStatus(AssetStatus assetStatus);

    Long countByAssetStatus(AssetStatus assetStatus);

    Optional<AssetModel> findByAssetCode(String assetCode);

    List<AssetModel> findByEmployee(EmployeeModel employee);

    Long countByAssignedStatus(AssignedStatus assignedStatus);

    @Query("SELECT SUM(a.cost) FROM AssetModel a WHERE a.assignedStatus = :assignedStatus")
    Double sumCostByAssignedStatus(AssignedStatus assignedStatus);

    Long countByAssetType(AssetType assetType);

    @Query("SELECT SUM(a.cost) FROM AssetModel a WHERE a.assetType = :assetType")
    Double sumCostByAssetType(AssetType assetType);}

