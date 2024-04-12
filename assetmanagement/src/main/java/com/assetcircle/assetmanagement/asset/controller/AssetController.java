package com.assetcircle.assetmanagement.asset.controller;

import com.assetcircle.assetmanagement.asset.dto.*;
import com.assetcircle.assetmanagement.asset.model.AssetModel;
import com.assetcircle.assetmanagement.asset.model.FixedAssetModel;
import com.assetcircle.assetmanagement.asset.model.ITAssetModel;
import com.assetcircle.assetmanagement.asset.service.AssetService;
import com.assetcircle.assetmanagement.asset.service.FixedAssetService;
import com.assetcircle.assetmanagement.asset.service.ITAssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = {
        "api/v1/asset"
},produces = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.APPLICATION_XML_VALUE
})
@Validated
@Slf4j
public class AssetController {

    private final AssetService assetService;
    private final ITAssetService itAssetService;
    private final FixedAssetService fixedAssetService;

    @Autowired
    public AssetController(AssetService assetService, ITAssetService itAssetService, FixedAssetService fixedAssetService) {
        this.assetService = assetService;
        this.itAssetService = itAssetService;
        this.fixedAssetService = fixedAssetService;
    }

    @PostMapping("/createasset")
    public ResponseEntity<AssetModel> createAsset(@RequestBody AssetModel assetModel) {
        AssetModel createdAsset = assetService.createAsset(assetModel);
        return new ResponseEntity<>(createdAsset, HttpStatus.CREATED);
    }

    @PostMapping("/createitasset")
    public ResponseEntity<ITAssetModel> createITAsset(@RequestBody ITAssetModel itAssetModel) {
        ITAssetModel createdAsset = itAssetService.createITAsset(itAssetModel);
        return new ResponseEntity<>(createdAsset, HttpStatus.CREATED);
    }

    @PostMapping("/createfixedasset")
    public ResponseEntity<FixedAssetModel> createFixedAsset(@RequestBody FixedAssetModel fixedAssetModel) {
        FixedAssetModel createdAsset = fixedAssetService.createFixedAsset(fixedAssetModel);
        return new ResponseEntity<>(createdAsset, HttpStatus.CREATED);
    }

    @PostMapping("/postAssetWithFixedAsset")
    public ResponseEntity<AssetWithFixedAssetDTO> postAssetWithFixedAsset(@RequestBody AssetWithFixedAssetDTO assetWithFixedAsset) {
        AssetDTO assetDTO = assetWithFixedAsset.getAsset();
        FixedAssetDTO fixedAssetDTO = assetWithFixedAsset.getFixedAsset();

        AssetWithFixedAssetDTO postedAssetWithFixedAssetDTO = assetService.postAssetWithFixedAsset(assetDTO, fixedAssetDTO);

        return new ResponseEntity<>(postedAssetWithFixedAssetDTO, HttpStatus.CREATED);
    }

    @PostMapping("/postAssetWithITAsset")
    public ResponseEntity<AssetWithITAssetDTO> postAssetWithITAsset(@RequestBody AssetWithITAssetDTO assetWithITAsset) {
        AssetDTO assetDTO = assetWithITAsset.getAsset();
        ITAssetDTO itAssetDTO = assetWithITAsset.getItAsset();

        AssetWithITAssetDTO postedAssetWithITAssetDTO = assetService.postAssetWithITAsset(assetDTO, itAssetDTO);

        return new ResponseEntity<>(postedAssetWithITAssetDTO, HttpStatus.CREATED);
    }

    @GetMapping("/allAssetWithITAssets")
    public ResponseEntity<List<AssetWithITAssetDTO>> getAllAssetsWithITAsset() {
        List<AssetWithITAssetDTO> allAssetsWithITAssetDTO = assetService.getAllAssetsWithITAssets();
        if (!allAssetsWithITAssetDTO.isEmpty()) {
            return new ResponseEntity<>(allAssetsWithITAssetDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/allAssetWithFixedAssets")
    public ResponseEntity<List<AssetWithFixedAssetDTO>> getAllAssetsWithFixedAsset() {
        List<AssetWithFixedAssetDTO> allAssetsWithfixedAssetDTO = assetService.getAllAssetsWithFixedAssets();
        if (!allAssetsWithfixedAssetDTO.isEmpty()) {
            return new ResponseEntity<>(allAssetsWithfixedAssetDTO, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
