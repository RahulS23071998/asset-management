package com.assetcircle.assetmanagement.asset.repository;

import com.assetcircle.assetmanagement.enums.AssetStatus;
import com.assetcircle.assetmanagement.asset.model.AssetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AssetRepository extends JpaRepository<AssetModel,Integer> {
    List<AssetModel> findAllByAssetStatus(AssetStatus assetStatus);

    long countByAssetStatus(AssetStatus assetStatus);
}
