package com.assetmanagement.desklite.asset.repository;

import com.assetmanagement.desklite.asset.models.AssetModel;
import com.assetmanagement.desklite.asset.models.ITAssetModel;
import com.assetmanagement.desklite.login.models.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ITAssetRepository extends JpaRepository<ITAssetModel,Integer> {

    ITAssetModel findByAssetModel(AssetModel asset);

    @Query("SELECT it FROM ITAssetModel it WHERE it.assetModel.assetId = :assetId")
    Optional<ITAssetModel> findByAssetId(@Param("assetId") Integer assetId);

}
