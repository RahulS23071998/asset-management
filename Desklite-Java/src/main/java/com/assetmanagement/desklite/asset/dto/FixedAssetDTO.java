package com.assetmanagement.desklite.asset.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FixedAssetDTO {
    private Integer id;
    private String color;
    private String graphicsCard;
    private String ram;
    private String rom;
    private String processor;
    private String os;
    private String osVersion;
    private String battery;
    private String chargerType;
    private String wireless;
    private String weight;
    private String dimension;
    private String ipaddress;
    private String connectorType;
    private String bluetoothVersion;
    private String chargingTime;
    private String capacity;
    private String size;
    private String watts;
    private String material;
    private String volt;
    private String length;
    private String simNumber;
    private String imeiNumber;
    private String generation;
}
