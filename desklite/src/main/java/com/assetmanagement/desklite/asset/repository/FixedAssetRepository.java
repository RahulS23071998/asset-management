package com.assetmanagement.desklite.asset.repository;

import com.assetmanagement.desklite.asset.models.AssetModel;
import com.assetmanagement.desklite.asset.models.FixedAssetModel;
import com.assetmanagement.desklite.login.models.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FixedAssetRepository extends JpaRepository<FixedAssetModel,Integer> {

    FixedAssetModel findByAssetModel(AssetModel assetModel);

    @Query("SELECT fa FROM FixedAssetModel fa WHERE fa.assetModel.assetId = :assetId")
    Optional<FixedAssetModel> findByAssetId(@Param("assetId") Integer assetId);
}
