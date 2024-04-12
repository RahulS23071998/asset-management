package com.assetmanagement.desklite.asset.repository;

import com.assetmanagement.desklite.asset.enums.AssetStatus;
import com.assetmanagement.desklite.asset.models.AssetModel;
import com.assetmanagement.desklite.login.models.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssetRepository extends JpaRepository<AssetModel,Integer> {
    List<AssetModel> findAllByAssetStatus(AssetStatus assetStatus);

    long countByAssetStatus(AssetStatus assetStatus);

    Optional<AssetModel> findByAssetCode(String assetCode);

    List<AssetModel> findByEmployee(EmployeeModel employee); //summa

}
