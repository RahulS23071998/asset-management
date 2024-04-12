package com.assetmanagement.desklite.asset.controller;

import com.assetmanagement.desklite.asset.dto.*;
import com.assetmanagement.desklite.asset.models.AssetModel;
import com.assetmanagement.desklite.asset.models.FixedAssetModel;
import com.assetmanagement.desklite.asset.models.ITAssetModel;
import com.assetmanagement.desklite.asset.service.AssetService;
import com.assetmanagement.desklite.asset.service.FixedAssetService;
import com.assetmanagement.desklite.asset.service.ITAssetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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
    public ResponseEntity<AssetDTO> createAsset(@RequestBody AssetDTO assetDTO) {
        AssetDTO createdAsset = assetService.createAsset(assetDTO);
        return new ResponseEntity<>(createdAsset, HttpStatus.CREATED);
    }

    @PostMapping("/createitasset")
    public ResponseEntity<ITAssetDTO> createITAsset(@RequestBody ITAssetDTO itAssetModel) {
        ITAssetDTO createdAsset = itAssetService.createITAsset(itAssetModel);
        return new ResponseEntity<>(createdAsset, HttpStatus.CREATED);
    }

    @PostMapping("/createfixedasset")
    public ResponseEntity<FixedAssetDTO> createFixedAsset(@RequestBody FixedAssetDTO fixedAssetModel) {
        FixedAssetDTO createdAsset = fixedAssetService.createFixedAsset(fixedAssetModel);
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

    @GetMapping("/assetsbycode")
    public ResponseEntity<?> getAssetByCode(@RequestParam String assetCode) {
        Optional<AssetWithITAndFixedDTO> assetOptional = assetService.getAssetWithITAndFixedByAssetCode(assetCode);
        if (assetOptional.isPresent()) {
            return new ResponseEntity<>(assetOptional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Asset not found", HttpStatus.NOT_FOUND);
        }
    }
}
