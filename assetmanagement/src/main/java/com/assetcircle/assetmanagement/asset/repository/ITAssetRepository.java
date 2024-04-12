package com.assetcircle.assetmanagement.asset.repository;


import com.assetcircle.assetmanagement.asset.model.AssetModel;
import com.assetcircle.assetmanagement.asset.model.ITAssetModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ITAssetRepository extends JpaRepository<ITAssetModel,Integer> {

    ITAssetModel findByAssetModel(AssetModel asset);

}
