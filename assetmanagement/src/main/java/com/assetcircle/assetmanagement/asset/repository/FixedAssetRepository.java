package com.assetcircle.assetmanagement.asset.repository;


import com.assetcircle.assetmanagement.asset.model.AssetModel;
import com.assetcircle.assetmanagement.asset.model.FixedAssetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixedAssetRepository extends JpaRepository<FixedAssetModel,Integer> {

    FixedAssetModel findByAssetModel(AssetModel assetModel);
}
